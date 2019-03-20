package com.example.nutritionalassistant

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.nutritionalassistant.helper.MyDBHandler
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
