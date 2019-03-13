package com.example.nutritionalassistant.helper

import android.util.Log
import com.example.nutritionalassistant.FindRecipesActivity
import org.json.JSONObject
import kotlin.collections.ArrayList

class ConvertToRecipes {


    fun convert(fileContents:String) {

        val dbHandler = MyDBHandler(this, null, null, 1)

        //Make sure content is similar to a JSON content
        if (!fileContents.matches(("/.*\\S.*/").toRegex()))
        {
//            Log.d("TAG", "20")
//            Log.d("TAG", "$fileContents")
//            Log.d("TAG", "${fileContents.javaClass}")
            Log.d("TAG", "21")
//            print("...........>>>>>>>>>>>>>" + fileContents.toString())

            var json = JSONObject(fileContents)

            val hitList = json.getJSONArray("hits")
            Log.d("TAG", "d11")
            //Extract each recipes values and add new object to list
            for (i in 0 until hitList.length())
            {
                var recipe: Recipe? = null
                val JSONRecipe = hitList.getJSONObject(i).getJSONObject("recipe")

                val uri = JSONRecipe.getString("uri")
                val label = JSONRecipe.getString("label")
                val image = JSONRecipe.getString("image")
                val url = JSONRecipe.getString("url")
                val share = JSONRecipe.getString("shareAs")

                val yieldServ = (JSONRecipe.getString("yield")).toFloat()
                val time = (JSONRecipe.getString("totalTime")).toFloat()
                val calories = (JSONRecipe.getString("calories")).toFloat()

                val dietArr = JSONRecipe.getJSONArray("dietLabels")
                val diet: ArrayList<String> = ArrayList()
                if (dietArr.length() == 0){
                    for (i in 0..(dietArr.length() - 1)) {
                        diet.add(dietArr.getJSONObject(i).toString())
                        Log.d("TAG", "d5")

                    }
                }


                val healthObjs = JSONRecipe.getJSONArray("healthLabels")
                var health = ""
                val sb = StringBuilder()

                for (i in 0..(healthObjs.length() - 1)) {
                    sb.append(healthObjs[i])
                }
                health = sb.toString()
                Log.d("TAG", "d6")

                val cautionObjs = JSONRecipe.getJSONArray("cautions")
                var caution = ""
                for (i in 0..(cautionObjs.length() - 1)) {
                    sb.append(cautionObjs[i])
                }
                caution = sb.toString()

                val ingredientsList = JSONRecipe.getJSONArray("ingredientLines")
                var ingredients = ""
                for (i in 0..(ingredientsList.length() - 1)) {
                    sb.append(ingredientsList[i])
                }
                ingredients = sb.toString()
                Log.d("TAG", "d7")

                recipe = Recipe(label, image, url, share, yieldServ, ingredients, calories, time)

                Log.d("TAG", "d8")
                dbHandler.addRecipe(recipe)
                Log.d("TAG", "d9")
            }

        }
    }
}
