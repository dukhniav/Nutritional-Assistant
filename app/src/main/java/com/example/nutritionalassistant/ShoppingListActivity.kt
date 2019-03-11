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
        shopItems.add(ShopItem("Tomato", "Grilled Cheese", 1.29, 55, 2))
        shopItems.add(ShopItem("Cheese", "Grilled Cheese", 3.99, 55,1))
        shopItems.add(ShopItem("Water", "Grilled Cheese", 0.0, 0,1))
        shopItems.add(ShopItem("Bread", "Grilled Cheese", 2.99, 399,1))
        shopItems.add(ShopItem("Potatoes", "Awesome Breakfast", 3.79, 450,1))
        shopItems.add(ShopItem("Eggs", "Awesome Breakfast", 2.79, 190,12))
        shopItems.add(ShopItem("Bacon", "Awesome Breakfast", 9.99, 800,1))
        shopItems.add(ShopItem("Pancakes", "Awesome Breakfast", 5.99, 555,1))
        shopItems.add(ShopItem("Milk", "Awesome Breakfast", 2.99, 155,1))





        //TODO: save and retrieve items to/from DB

        var adapter = MyShoppingListAdapter(shopItems)
        rv.adapter = adapter
    }
}
