package com.example.nutritionalassistant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.nutritionalassistant.helper.MyShoppingListAdapter
import com.example.nutritionalassistant.helper.ShopItem

class ShoppingListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)

        val rv = findViewById<RecyclerView>(R.id.recyclerViewShoppingList)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val shopItems = ArrayList<ShopItem>()
        shopItems.add(ShopItem("Tomato", "Grilled Cheese"))
        shopItems.add(ShopItem("Cheese", "Grilled Cheese"))
        shopItems.add(ShopItem("Water", "Grilled Cheese"))
        shopItems.add(ShopItem("Bread", "Grilled Cheese"))
        shopItems.add(ShopItem("Potatoes", "Awesome Breakfast"))
        shopItems.add(ShopItem("Eggs", "Awesome Breakfast"))
        shopItems.add(ShopItem("Bacon", "Awesome Breakfast"))
        shopItems.add(ShopItem("Pancakes", "Awesome Breakfast"))
        shopItems.add(ShopItem("Milk", "Awesome Breakfast"))





        //TODO: save and retrieve items to/from DB

        var adapter = MyShoppingListAdapter(shopItems)
        rv.adapter = adapter
    }
}
