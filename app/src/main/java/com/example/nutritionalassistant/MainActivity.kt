package com.example.nutritionalassistant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        settingsBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent) }
        recipeBtn.setOnClickListener {
            val intent = Intent(this, FindRecipesActivity::class.java)
            startActivity(intent) }
        shoppingListBtn.setOnClickListener {
            val intent = Intent(this, ShoppingListActivity::class.java)
            startActivity(intent) }
        myRecipesBtn.setOnClickListener {
            val intent = Intent(this, MyRecipesActivity::class.java)
            intent.putExtra("context", "MainActivity")
            startActivity(intent) }
        educationalReferencesBtn.setOnClickListener {
            val intent = Intent(this, EducationalReferencesActivity::class.java)
            startActivity(intent) }
    }
}
