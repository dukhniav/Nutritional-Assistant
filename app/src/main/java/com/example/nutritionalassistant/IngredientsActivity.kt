package com.example.nutritionalassistant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.nutritionalassistant.helper.MyDBHandler
import com.example.nutritionalassistant.helper.RecipeAdapter
import com.example.nutritionalassistant.helper.ShopItem
import com.example.nutritionalassistant.helper.ShoppingListAdapter
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

        var adapter = ShoppingListAdapter(ingredientArray)
        rv.adapter = adapter
    }
}
