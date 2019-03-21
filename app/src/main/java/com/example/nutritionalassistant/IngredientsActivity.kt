package com.example.nutritionalassistant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.Toast
import com.example.nutritionalassistant.helper.*
import com.example.nutritionalassistant.helper.ShoppingListAdapter.ViewHolder
import kotlinx.android.synthetic.main.activity_ingredients.*
import kotlinx.android.synthetic.main.activity_shopping_list.*
import kotlinx.android.synthetic.main.shopping_list_row.*
import java.util.ArrayList

class IngredientsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        val rv = findViewById<RecyclerView>(R.id.recyclerViewIngredients)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?

        val dbHandler = MyDBHandler(this, null, null, 1)

        var bundle = Bundle()
        bundle = intent.getBundleExtra("bundle")

        val ingredientArray: ArrayList<ShopItem> = bundle.getParcelableArrayList<ShopItem>("shopItemArr")

        var adapter = IngredientsListAdapter(ingredientArray)
        rv.adapter = adapter

        ingredientsAddAll.setOnClickListener {
            for (i in 0..(ingredientArray.size - 1)) {
                dbHandler.addShopItem(ingredientArray[i])
                Toast.makeText(this, "Added to shopping list!", Toast.LENGTH_LONG).show()

            }
        }
    }
}
