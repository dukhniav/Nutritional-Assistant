package com.example.nutritionalassistant

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.nutritionalassistant.helper.MyDBHandler
import com.example.nutritionalassistant.helper.ShopItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_recipe.*

class ShowRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_recipe)

        val dbHandler = MyDBHandler(this, null, null, 1)
        val currentRecipe = dbHandler.findRecipe(intent.getStringExtra("title"))
        val recipeUrl = currentRecipe?.url

        showRecipeTitle.text = currentRecipe?.label

        showRecipeSource.text = currentRecipe?.source


        showRecipeSave.setOnClickListener {
            if (currentRecipe != null) {
                dbHandler.addMyRecipe(currentRecipe)
                Toast.makeText(this, "Recipe added!", Toast.LENGTH_LONG).show()
            }
        }

        Log.d("TAG", "Ingredients: " + currentRecipe?.ingredientsLines.toString())

        //Temp - TODO: replace with button
        textView15.setOnClickListener {
            val intent = Intent(this, IngredientsActivity::class.java)

            var ingredientArr: List<String>
            val shopItemArr: ArrayList<ShopItem> = ArrayList()
            val ingredientLines = currentRecipe?.ingredientsLines

            ingredientArr = ingredientLines?.split(delimiters = *arrayOf("\n"))!!

            for (i in 0..(ingredientArr.size - 1)) {
                shopItemArr.add(ShopItem(ingredientArr[i], currentRecipe.label.toString()))
            }
            val bundle : Bundle = Bundle()
            bundle.putParcelableArrayList("shopItemArr", shopItemArr)

            intent.putExtra("bundle", bundle)

            startActivity(intent)

        }

        showRecipeSource.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(recipeUrl)
            startActivity(i)
        }


        Picasso.with(this)
            .load(currentRecipe?.image)
            .into(showRecipeImage)
    }
}

private fun Bundle.putParcelableArrayList(s: String, shopItemArr: ArrayList<ShopItem>) {}
