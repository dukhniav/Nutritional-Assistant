package com.example.nutritionalassistant

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.SeekBar
import android.widget.TextView
import com.example.nutritionalassistant.helper.ConvertToRecipes
import com.example.nutritionalassistant.helper.MyDBHandler
import com.example.nutritionalassistant.helper.recipe_search
import kotlinx.android.synthetic.main.activity_find_recipes.*
import android.os.StrictMode



class FindRecipesActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener{
    private var cookText: TextView? = null
    private var cookSeekbarView: SeekBar? = null
    private var caloriesText: TextView? = null
    private var caloriesSeekbarView: SeekBar? = null
    private var ingredientsText: TextView? = null
    private var ingredientsSeekbarView: SeekBar? = null
    private var servingsText: TextView? = null
    private var servingsSeekbarView: SeekBar? = null

    private var minCook = 1
    private var maxCook = 720
    private var minCalories = 1
    private var maxCalories = 10000
    private var minIngredients = 1
    private var maxIngredients = 100
    private var minServings = 1
    private var maxServings = 50

    override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
        when(p0!!.id) {
            cookSeekbarView!!.id -> cookText!!.text = scale(progress,minCook,maxCook).toString()
            caloriesSeekbarView!!.id -> caloriesText!!.text = scale(progress,minCalories,maxCalories).toString()
            ingredientsSeekbarView!!.id -> ingredientsText!!.text = scale(progress,minIngredients,maxIngredients).toString()
            servingsSeekbarView!!.id -> servingsText!!.text = scale(progress,minServings,maxServings).toString()
        }

        buildQuery()
    }
    /* -----------
     * Build Query
     * -----------
     * q       : search string
     * from    : index of return to start at
     * to      : index of return to end at (returns a total of 'from' - 'to' entries)
     * ingr    : max number of ingredients
     * diet    : "balanced", "high-protein", "high-fiber", "low-fat", "low-carb", or "low-sodium"
     * minCalories: minimum number of calories in the recipe
     * maxCalories: maximum number of calories
     * time    : + time.toString()
     * excluded:" + excluded
     *
     * https://developer.edamam.com/edamam-docs-recipe-api
    */
    fun buildQuery(): Array<String> {

        // rQ required for API
        val rQ : String = if (recipeSearch.text != null){
            "q:" + recipeSearch.text
        } else {
            "q:" + " "
        }

        val rFrom = "from:" + 0 //starting from 0
        val rTo = "to:" + 100 // query results to 100
        val rIngr = "ingr:" + ingredientsText!!.text
        val rDiet = "diet:" + "balanced" // temporary until add options to pick
        val rCalories = "calories:" + caloriesText!!.text
        val rTime = "time:" + cookText!!.text
        val rExcluded = "excluded:" // nothing excluded
      
        return arrayOf(rQ, rFrom, rTo, rIngr, rDiet, rCalories, rTime, rExcluded)

    }
    private fun scale(i: Int, min: Int, max: Int): Int{
        return ((i.toDouble() / 100) * (max.toDouble() - min.toDouble())).toInt() + min
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {}
    override fun onStopTrackingTouch(p0: SeekBar?) {}

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_recipes)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // shared pref
        val pref = getSharedPreferences("save", Context.MODE_PRIVATE)
        val editor = pref.edit()

        cookText = this.seekCookText
        cookSeekbarView = this.seekCook
        cookSeekbarView!!.setOnSeekBarChangeListener(this)

        caloriesText = this.seekCaloriesText
        caloriesSeekbarView = this.seekCalories
        caloriesSeekbarView!!.setOnSeekBarChangeListener(this)

        ingredientsText = this.seekIngredientsText
        ingredientsSeekbarView = this.seekIngredients
        ingredientsSeekbarView!!.setOnSeekBarChangeListener(this)

        servingsText = this.seekServingsText
        servingsSeekbarView = this.seekServings
        servingsSeekbarView!!.setOnSeekBarChangeListener(this)

        seekCook.progress = pref.getInt("cook", 0)
        seekCalories.progress = pref.getInt("calories", 0)
        seekIngredients.progress = pref.getInt("difficulty", 0)
        seekServings.progress = pref.getInt("servings", 0)
        switchFuture.isChecked = pref.getBoolean("check", false)


        showRecipesBtn.setOnClickListener {
            val dbHandler = MyDBHandler(this, null, null, 1)
            val intent = Intent(this, ShowRecipesActivity::class.java)

            val file = recipe_search.recipeSearch(buildQuery())

            // convert json file to array of recipe objects
            val converter = ConvertToRecipes()
            val recipeAr = converter.convert(file)

            // clear table before adding new recipes
            dbHandler.deleteAllRecipes()

            // add recipes to DB
            for(i in recipeAr){
                dbHandler.addRecipe(i)
            }

            // Shared Preferences
            if (switchFuture.isChecked) {
                editor.putBoolean("check", true)
                editor.putInt("cook", seekCook.progress)
                editor.putInt("calories", seekCalories.progress)
                editor.putInt("ingredients", seekIngredients.progress)
                editor.putInt("servings", seekServings.progress)
                editor.apply()
            } else {
                editor.clear()
                editor.remove("check")
                editor.remove("cook")
                editor.remove("calories")
                editor.remove("ingredients")
                editor.remove("servings")
                editor.apply()
            }

            startActivity(intent)
        }
    }
}
