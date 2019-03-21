package com.example.nutritionalassistant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.example.nutritionalassistant.helper.MyDBHandler
import com.example.nutritionalassistant.helper.MyRecipeAdapter

class MyRecipesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_recipes)

        val rv = findViewById<RecyclerView>(R.id.recyclerViewMyRecipes)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val dbHandler = MyDBHandler(this, null, null, 1)

        val recipes = dbHandler.getAllMyRecipes()

        var adapter = MyRecipeAdapter(recipes)
        rv.adapter = adapter
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        this.finish()
        startActivity(intent)
    }
}