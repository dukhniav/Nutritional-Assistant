package com.example.nutritionalassistant

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.nutritionalassistant.helper.ConvertToRecipes
import com.example.nutritionalassistant.helper.MyDBHandler
import com.example.nutritionalassistant.helper.MyRecipeAdapter

class ShowRecipesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_recipes)

//        applicationContext= ConvertToRecipes.applicationContext

        val rv = findViewById<RecyclerView>(R.id.recyclerViewRecipes)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val dbHandler = MyDBHandler(this, null, null, 1)

        val recipes = dbHandler.getAllRecipes()



        //TODO: save and retrieve recipes to/from DB

        var adapter = MyRecipeAdapter(recipes)
        rv.adapter = adapter
    }
}