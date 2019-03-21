package com.example.nutritionalassistant.helper

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.example.nutritionalassistant.R

class IngredientsListAdapter(val shoppingList: ArrayList<ShopItem>): RecyclerView.Adapter<IngredientsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.ingredients_list_row, parent, false)
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder?.itemName?.text = shoppingList[position].name
        holder?.itemRecipe?.text = shoppingList[position].recipe




//        holder.setOnClickListener(View.OnClickListener {
//            holder.itemCheck.setChecked(
//                !(holder as MyViewHolder).checkbox.isChecked()
//            )
//            if ((holder as MyViewHolder).checkbox.isChecked()) {
//                onItemClick.onItemCheck(currentItem)
//            } else {
//                onItemClick.onItemUncheck(currentItem)
//            }
//        })
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemName: TextView = itemView.findViewById(R.id.ingredientsItem)
        val itemRecipe: TextView = itemView.findViewById(R.id.ingredientsRecipe)
        val itemCheck = itemView.findViewById<CheckBox>(R.id.ingredientsDone)
    }
}