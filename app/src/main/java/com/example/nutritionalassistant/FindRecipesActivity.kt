package com.example.nutritionalassistant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_find_recipes.*

class FindRecipesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_recipes)

        showRecipesBtn.setOnClickListener {
            val intent = Intent(this, ShowRecipesActivity::class.java)
            startActivity(intent) }
    }

    //TODO: save values from sliders for future searches

}
