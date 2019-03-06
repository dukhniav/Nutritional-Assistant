package com.example.nutritionalassistant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import com.example.nutritionalassistant.helper.Recipe
import com.example.nutritionalassistant.helper.recipe_search
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
        when(p0!!.id) {
            cookSeekbarView!!.id -> cookText!!.text = progress.toString()
            budgetSeekbarView!!.id -> budgetText!!.text = progress.toString()
            difficultySeekbarView!!.id -> difficultyText!!.text = progress.toString()
            servingsSeekbarView!!.id -> servingsText!!.text = progress.toString()
        }

        buildQuery(q = "chicken", to = 100)
    }
    /* -----------
     * Build Query
     * -----------
     * q       : search string
     * from    : index of return to start at
     * to      : index of return to end at (returns a total of 'from' - 'to' entries)
     * ingr    : max number of ingedients
     * diet    : "balanced", "high-protein", "high-fiber", "low-fat", "low-carb", or "low-sodium"
     * minCalories: minimum number of calories in the recipe
     * maxCalories: maximum number of calories
     * time    : + time.toString()
     * excluded:" + excluded
     *
     * https://developer.edamam.com/edamam-docs-recipe-api
    */
    fun buildQuery(q: String = "", from: Int = 0, to: Int = 10, ingr: Int = 99,
                   diet: String = "balanced", maxCalories: Int = 9999,
                   time: Int = 999, excluded: String = ""): Array<String> {
        val rQ = "q:" + q
        val rFrom = "from:" + from.toString()
        val rTo = "to:" + to.toString()
        val rIngr = "ingr:" + ingr.toString()
        val rDiet = "diet:" + diet
        val rCalories = "calories:" + maxCalories.toString()
        val rTime = "time:" + time.toString()
        val rExcluded = "excluded:" + excluded

        return arrayOf(rQ, rFrom, rTo, rIngr, rDiet, rCalories, rTime, rExcluded)
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
            //val file = recipe_search.recipeSearch(buildQuery())

            //intent.putParcelableArrayListExtra("recipes", file)
            startActivity(intent) }
    }

    //TODO: save values from sliders for future searches


}
