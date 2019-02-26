package com.example.nutritionalassistant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.nutritionalassistant.helper.MyRecipeAdapter
import com.example.nutritionalassistant.helper.Recipe

class ShowRecipesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_recipes)

        val rv = findViewById<RecyclerView>(R.id.recyclerViewRecipes)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val recipes = ArrayList<Recipe>()
        recipes.add(Recipe("Stew", "5"))
        recipes.add(Recipe("Potatos", "6"))
        recipes.add(Recipe("Kale", "7"))
        recipes.add(Recipe("Tomato", "8"))
        recipes.add(Recipe("Tomato", "1"))
        recipes.add(Recipe("Lettuce", "18"))
        recipes.add(Recipe("Peanuts", "83"))

        //TODO: save and retrieve recipes to/from DB

        var adapter = MyRecipeAdapter(recipes)
        rv.adapter = adapter
    }
}