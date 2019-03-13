package com.example.nutritionalassistant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.nutritionalassistant.helper.ConvertToRecipes
import com.example.nutritionalassistant.helper.MyDBHandler
import com.example.nutritionalassistant.helper.MyRecipeAdapter

class MyRecipesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_recipes)

        val rv = findViewById<RecyclerView>(R.id.recyclerViewMyRecipes)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        val dbHandler = MyDBHandler(this, null, null, 1)

        val recipes = dbHandler.getAllRecipes()

//        recipes.add(Recipe("My Stew","","","","", 0.0F,"",0.0F,0.0F))
//        recipes.add(Recipe("My Stew1","","","","", 0.0F,"",0.0F,0.0F))
//        recipes.add(Recipe("My Stew2","","","","", 0.0F,"",0.0F,0.0F))
//        recipes.add(Recipe("My Stew3","","","","", 0.0F,"",0.0F,0.0F))
//        recipes.add(Recipe("My Stew4","","","","", 0.0F,"",0.0F,0.0F))
//        recipes.add(Recipe("My Stew5","","","","", 0.0F,"",0.0F,0.0F))
//        recipes.add(Recipe("My Stew6","","","","", 0.0F,"",0.0F,0.0F))

        //TODO: save and retrieve recipes to/from DB

        var adapter = MyRecipeAdapter(recipes)
        rv.adapter = adapter
    }
}