package com.example.nutritionalassistant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.nutritionalassistant.helper.MyRecipeAdapter
import com.example.nutritionalassistant.helper.Recipe

class MyRecipesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_recipes)

        val rv = findViewById<RecyclerView>(R.id.recyclerViewMyRecipes)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val recipes = ArrayList<Recipe>()
        recipes.add(Recipe("My Stew", "5"))
        recipes.add(Recipe("My Potatos", "6"))
        recipes.add(Recipe("My Kale", "7"))
        recipes.add(Recipe("My Tomato", "8"))
        recipes.add(Recipe("My Tomato", "1"))
        recipes.add(Recipe("My Lettuce", "18"))
        recipes.add(Recipe("My Peanuts", "83"))

        //TODO: save and retrieve recipes to/from DB

        var adapter = MyRecipeAdapter(recipes)
        rv.adapter = adapter
    }
}