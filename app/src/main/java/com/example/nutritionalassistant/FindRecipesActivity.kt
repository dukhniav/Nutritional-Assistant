package com.example.nutritionalassistant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_find_recipes.*

class FindRecipesActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener{
    private var cookText: TextView? = null
    private var cookSeekbarView: SeekBar? = null
    private var budgetText: TextView? = null
    private var budgetSeekbarView: SeekBar? = null
    private var difficultyText: TextView? = null
    private var difficultySeekbarView: SeekBar? = null
    private var servingsText: TextView? = null
    private var servingsSeekbarView: SeekBar? = null



    override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {

        cookText!!.text = progress.toString()
        budgetText!!.text = progress.toString()
        difficultyText!!.text = progress.toString()
        servingsText!!.text = progress.toString()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {}
    override fun onStopTrackingTouch(p0: SeekBar?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_recipes)

        cookText = this.seekCookText
        cookSeekbarView = this.seekCook
        cookSeekbarView!!.setOnSeekBarChangeListener(this)

        budgetText = this.seekBudgetText
        budgetSeekbarView = this.seekBudget
        budgetSeekbarView!!.setOnSeekBarChangeListener(this)

        difficultyText = this.seekDifficultyText
        difficultySeekbarView = this.seekDifficulty
        difficultySeekbarView!!.setOnSeekBarChangeListener(this)

        servingsText = this.seekServingsText
        servingsSeekbarView = this.seekServings
        servingsSeekbarView!!.setOnSeekBarChangeListener(this)

        showRecipesBtn.setOnClickListener {
            val intent = Intent(this, ShowRecipesActivity::class.java)
            startActivity(intent) }
    }

    //TODO: save values from sliders for future searches

}
