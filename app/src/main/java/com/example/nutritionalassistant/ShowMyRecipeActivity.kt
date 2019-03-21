package com.example.nutritionalassistant

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import com.example.nutritionalassistant.helper.MyDBHandler
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_my_recipe.*
import kotlinx.android.synthetic.main.activity_show_recipe.*
import kotlinx.android.synthetic.main.recipe_row.view.*

class ShowMyRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_my_recipe)

        val dbHandler = MyDBHandler(this, null, null, 1)
        val currentRecipe = dbHandler.findRecipe(intent.getStringExtra("title"))
        val recipeUrl = currentRecipe?.url

        showMyRecipeTitle.text = currentRecipe?.label

        showMyRecipeSource.text = currentRecipe?.source


        showMyRecipeDelete.setOnClickListener {
            dbHandler.deleteMyRecipe(currentRecipe?.label.toString())
            Toast.makeText(this, "Recipe removed!", Toast.LENGTH_LONG).show()
            // go back to previous activity
            val intent = Intent(this, MyRecipesActivity::class.java)
            startActivity(intent)
        }

        showMyRecipeSource.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(recipeUrl)
            startActivity(i)
        }


        Picasso.with(this)
                .load(currentRecipe?.image)
                .into(showMyRecipeImage)
    }
}
