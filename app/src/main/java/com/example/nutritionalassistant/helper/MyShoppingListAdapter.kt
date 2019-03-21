package com.example.nutritionalassistant.helper

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nutritionalassistant.R
import com.example.nutritionalassistant.helper.Recipe

class MyShoppingListAdapter(private val shoppingList: ArrayList<ShopItem>): RecyclerView.Adapter<MyShoppingListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.shopping_list_row, parent, false)
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.shopListName?.text = shoppingList[position].name
        holder?.shopListRecipe?.text = shoppingList[position].recipe
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val shopListName = itemView.findViewById<TextView>(R.id.shoppingListItem)!!
        val shopListRecipe = itemView.findViewById<TextView>(R.id.shoppingListRecipe)!!
    }

}