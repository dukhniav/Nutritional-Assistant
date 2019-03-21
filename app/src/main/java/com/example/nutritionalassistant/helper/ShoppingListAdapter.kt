package com.example.nutritionalassistant.helper

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.nutritionalassistant.MainActivity
import com.example.nutritionalassistant.R
import com.example.nutritionalassistant.ShowMyRecipeActivity
import com.example.nutritionalassistant.helper.Recipe
import kotlinx.android.synthetic.main.activity_shopping_list.view.*
import kotlinx.android.synthetic.main.recipe_row.view.*

class ShoppingListAdapter(val shoppingList: ArrayList<ShopItem>): RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {
    val context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.shopping_list_row, parent, false)
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.shopListName?.text = shoppingList[position].name
        holder?.shopListRecipe?.text = shoppingList[position].recipe

        holder.shopListCheck.setOnClickListener {
            if(MyDBHandler(holder.shopListCheck.context, null, null, 1).deleteShoppingListItem(holder?.shopListName?.text.toString())) {
                Toast.makeText(holder.shopListCheck.context, "Ingredient removed!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val shopListName = itemView.findViewById<TextView>(R.id.shoppingListItem)!!
        val shopListRecipe = itemView.findViewById<TextView>(R.id.shoppingListRecipe)!!
        val shopListCheck = itemView.findViewById<CheckBox>(R.id.shoppingListDone)
        val shopListRemoveAll = itemView.findViewById<Button>(R.id.shoppingListBtn)


    }
}