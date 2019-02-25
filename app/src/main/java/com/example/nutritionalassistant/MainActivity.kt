package com.example.nutritionalassistant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recipeBtn.setOnClickListener {
            val intent = Intent(this, FindRecipesActivity::class.java)
            startActivity(intent) }
        shoppingListBtn.setOnClickListener {
            val intent = Intent(this, ShoppingListActivity::class.java)
            startActivity(intent) }
        mealPlannerBtn.setOnClickListener {
            val intent = Intent(this, MealPlannerActivity::class.java)
            startActivity(intent) }
        educationalReferencesBtn.setOnClickListener {
            val intent = Intent(this, EducationalReferencesActivity::class.java)
            startActivity(intent) }
    }
}
