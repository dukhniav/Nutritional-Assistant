package com.example.nutritionalassistant.helper

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nutritionalassistant.R
import com.example.nutritionalassistant.ShowRecipeActivity
import com.example.nutritionalassistant.helper.Recipe
import kotlinx.android.synthetic.main.recipe_row.view.*

class MyRecipeAdapter(val recipeList: ArrayList<Recipe>): RecyclerView.Adapter<MyRecipeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.recipe_row, parent, false)
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.recipeName?.text = recipeList[position].label

        holder.itemView.recipe_row.setOnClickListener{
            val intent = Intent(holder.itemView.recipe_row.context, ShowRecipeActivity::class.java)
            holder.itemView.recipe_row.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val recipeName = itemView.findViewById<TextView>(R.id.recipeName)!!
    }
}