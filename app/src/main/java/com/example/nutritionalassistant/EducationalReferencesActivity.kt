package com.example.nutritionalassistant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.nutritionalassistant.references.FoodPreparation
import com.example.nutritionalassistant.references.FoodStorage
import com.example.nutritionalassistant.references.NutritionalFacts
import com.example.nutritionalassistant.references.Utensils
import kotlinx.android.synthetic.main.activity_educational_references.*

class EducationalReferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_educational_references)

        nutritionBtn.setOnClickListener {
            val intent = Intent(this, NutritionalFacts::class.java)
            startActivity(intent) }
        utensilBtn.setOnClickListener {
            val intent = Intent(this, Utensils::class.java)
            startActivity(intent) }
        foodPrepBtn.setOnClickListener {
            val intent = Intent(this, FoodPreparation::class.java)
            startActivity(intent) }
        foodStorageBtn.setOnClickListener {
            val intent = Intent(this, FoodStorage::class.java)
            startActivity(intent) }
    }
}
