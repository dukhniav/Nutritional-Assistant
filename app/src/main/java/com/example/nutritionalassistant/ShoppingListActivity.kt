package com.example.nutritionalassistant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.Toast
import com.example.nutritionalassistant.helper.MyDBHandler
import com.example.nutritionalassistant.helper.ShopItem
import com.example.nutritionalassistant.helper.ShoppingListAdapter
import kotlinx.android.synthetic.main.activity_shopping_list.*
import kotlinx.android.synthetic.main.shopping_list_row.*

class ShoppingListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)

        val rv = findViewById<RecyclerView>(R.id.recyclerViewShoppingList)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val dbHandler = MyDBHandler(this, null, null, 1)

        var shopItems : ArrayList<ShopItem> = dbHandler.getShoppingList()

        shopListRemoveAll.setOnClickListener {
            MyDBHandler(this, null, null, 1).deleteShoppingList()
            Toast.makeText(this, "Shopping list cleared!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }

        var adapter = ShoppingListAdapter(shopItems)
        rv.adapter = adapter
    }
}
