package com.example.nutritionalassistant.helper

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.nutritionalassistant.R
import com.example.nutritionalassistant.ShowRecipeActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_row.view.*

class RecipeAdapter(val recipeList: ArrayList<Recipe>): RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.recipe_row, parent, false)
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.recipeName?.text = recipeList[position].label
        holder?.recipeTime?.text = recipeList[position].totalTime?.toInt().toString()
        holder?.recipeCalories?.text = recipeList[position].calories?.toInt().toString()
        holder?.recipeServings?.text = recipeList[position].yieldServings?.toInt().toString()

        Picasso.with(holder.itemView.context)
            .load(recipeList[position].image)
            .into(holder.recipeImage)

        Log.d("TAG", "URL: " + recipeList[position].image)

        holder.itemView.recipe_row.setOnClickListener{
            val intent = Intent(holder.itemView.recipe_row.context, ShowRecipeActivity::class.java)
            intent.putExtra("title", holder.recipeName.text as String?)
            holder.itemView.recipe_row.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val recipeName = itemView.findViewById<TextView>(R.id.recipeName)!!
        val recipeImage = itemView.findViewById<ImageView>(R.id.recipeImage)!!
        val recipeTime = itemView.findViewById<TextView>(R.id.time)!!
        val recipeCalories = itemView.findViewById<TextView>(R.id.calories)!!
        val recipeServings = itemView.findViewById<TextView>(R.id.servings)!!
    }
}