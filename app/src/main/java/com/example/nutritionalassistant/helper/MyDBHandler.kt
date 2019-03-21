package com.example.nutritionalassistant.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MyDBHandler(
        context: Context,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_RECIPE_TABLE)
        db?.execSQL(CREATE_MY_RECIPE_TABLE)
        db?.execSQL(CREATE_TABLE_SHOPPING_LIST)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // on upgrade, drop old tables
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_RECIPE")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MY_RECIPE")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_SHOPPING_LIST")
        onCreate(db)
    }

    // ----------------------------- RECIPE --------------------------------------
    fun addRecipe(recipe: Recipe) {
        Log.d("TAG", "AddRecipe")
        val values = ContentValues()
        values.put(COLUMN_RECIPE_LABEL, recipe.label)
        values.put(COLUMN_RECIPE_IMAGE, recipe.image)
        values.put(COLUMN_RECIPE_URL, recipe.url)
        values.put(COLUMN_RECIPE_SHARE, recipe.shareAs)
        values.put(COLUMN_RECIPE_YIELDSERVINGS, recipe.yieldServings)
        values.put(COLUMN_RECIPE_INGREDIENTLINES, recipe.ingredientsLines)
        values.put(COLUMN_RECIPE_CALORIES, recipe.calories)
        values.put(COLUMN_RECIPE_TOTALTIME, recipe.totalTime)
        values.put(COLUMN_RECIPE_SOURCE, recipe.source)

        Log.d("TAG", "Before WritableDatabase")
        val db = this.writableDatabase
        Log.d("TAG", "WritableDatabase")

        db.insert(TABLE_RECIPE, null, values)
        db.close()
        Log.d("TAG", "works")
    }

    fun findRecipe(recipeLabel: String): Recipe? {
        Log.d("TAG", "findRecipe")
        val db = this.writableDatabase

        val query = "SELECT * FROM $TABLE_RECIPE WHERE $COLUMN_RECIPE_LABEL = \"$recipeLabel\""

        val cursor = db.rawQuery(query, null)

        var recipe: Recipe? = null

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val label = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_LABEL))
            val image = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_IMAGE))
            val url = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_URL))
            val shareAs = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_SHARE))
            val yieldServings = cursor.getFloat(cursor.getColumnIndex(COLUMN_RECIPE_YIELDSERVINGS))
            val ingredients = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_INGREDIENTLINES))
            val calories = cursor.getFloat(cursor.getColumnIndex(COLUMN_RECIPE_CALORIES))
            val totalTime = cursor.getFloat(cursor.getColumnIndex(COLUMN_RECIPE_TOTALTIME))
            val source = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_SOURCE))

            recipe = Recipe(label, image, url, shareAs, yieldServings, ingredients, calories, totalTime, source)
            cursor.close()
        }
        db.close()
        return recipe
    }

    fun deleteAllRecipes() {
        Log.d("TAG", "deleteAllRecipes")
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_RECIPE")
        db.close()
    }
    fun getAllRecipes() : ArrayList<Recipe> {
        Log.d("TAG", "getAllRecipes")
        val db = this.writableDatabase
        val recArray = ArrayList<Recipe>()

        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_RECIPE", null)

        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val recLabel = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_LABEL))
                val recImage = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_IMAGE))
                val recUrl = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_URL))
                val recShare = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_SHARE))
                val recYield = cursor.getFloat(cursor.getColumnIndex(COLUMN_RECIPE_YIELDSERVINGS))
                val recIngredient = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_INGREDIENTLINES))
                val recCalories = cursor.getFloat(cursor.getColumnIndex(COLUMN_RECIPE_CALORIES))
                val recTime = cursor.getFloat(cursor.getColumnIndex(COLUMN_RECIPE_TOTALTIME))
                val recSource = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_SOURCE))

                val recipe = Recipe(recLabel, recImage, recUrl, recShare, recYield, recIngredient, recCalories, recTime, recSource)
                recArray.add(recipe)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return recArray
    }
    fun deleteRecipe(recipeLabel: String): Boolean {
        var result = false
        val query = "SELECT * FROM $TABLE_RECIPE WHERE $COLUMN_RECIPE_LABEL = \"$recipeLabel\""

        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(TABLE_RECIPE, "$KEY_ID = ?",
                arrayOf(id.toString()))

            cursor.close()
            result = true
        }
        db.close()
        return result
    }
    /* ---------------------------MY RECIPE---------------------------------- */

    fun addMyRecipe(recipe: Recipe) {
        Log.d("TAG", "AddMyRecipe")
        val values = ContentValues()
        values.put(COLUMN_RECIPE_LABEL, recipe.label)
        values.put(COLUMN_RECIPE_IMAGE, recipe.image)
        values.put(COLUMN_RECIPE_URL, recipe.url)
        values.put(COLUMN_RECIPE_SHARE, recipe.shareAs)
        values.put(COLUMN_RECIPE_YIELDSERVINGS, recipe.yieldServings)
        values.put(COLUMN_RECIPE_INGREDIENTLINES, recipe.ingredientsLines)
        values.put(COLUMN_RECIPE_CALORIES, recipe.calories)
        values.put(COLUMN_RECIPE_TOTALTIME, recipe.totalTime)
        values.put(COLUMN_RECIPE_SOURCE, recipe.source)

        Log.d("TAG", "Before WritableDatabase")
        val db = this.writableDatabase
        Log.d("TAG", "WritableDatabase")

        db.insert(TABLE_MY_RECIPE, null, values)
        db.close()
        Log.d("TAG", "works")
    }

    fun findMyRecipe(recipeLabel: String): Recipe? {
        Log.d("TAG", "findRecipe")
        val db = this.writableDatabase

        val query = "SELECT * FROM $TABLE_MY_RECIPE WHERE $COLUMN_RECIPE_LABEL = \"$recipeLabel\""

        val cursor = db.rawQuery(query, null)

        var recipe: Recipe? = null

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val label = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_LABEL))
            val image = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_IMAGE))
            val url = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_URL))
            val shareAs = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_SHARE))
            val yieldServings = cursor.getFloat(cursor.getColumnIndex(COLUMN_RECIPE_YIELDSERVINGS))
            val ingredients = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_INGREDIENTLINES))
            val calories = cursor.getFloat(cursor.getColumnIndex(COLUMN_RECIPE_CALORIES))
            val totalTime = cursor.getFloat(cursor.getColumnIndex(COLUMN_RECIPE_TOTALTIME))
            val source = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_SOURCE))

            recipe = Recipe(label, image, url, shareAs, yieldServings, ingredients, calories, totalTime, source)
            cursor.close()
        }
        db.close()
        return recipe
    }

    fun deleteAllMyRecipes() {
        Log.d("TAG", "deleteAllRecipes")
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_MY_RECIPE")
        db.close()
    }

    fun deleteMyRecipe(recipeLabel: String): Boolean {
        var result = false
        val query = "SELECT * FROM $TABLE_MY_RECIPE WHERE $COLUMN_RECIPE_LABEL = \"$recipeLabel\""

        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val label = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_LABEL))
            db.delete(TABLE_MY_RECIPE, "$COLUMN_RECIPE_LABEL = ?", arrayOf(label))

            cursor.close()
            result = true
        }
        db.close()
        return result
    }
    fun getAllMyRecipes() : ArrayList<Recipe> {
        Log.d("TAG", "getAllRecipes")
        val db = this.writableDatabase
        val recArray = ArrayList<Recipe>()

        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_MY_RECIPE", null)

        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val recLabel = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_LABEL))
                val recImage = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_IMAGE))
                val recUrl = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_URL))
                val recShare = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_SHARE))
                val recYield = cursor.getFloat(cursor.getColumnIndex(COLUMN_RECIPE_YIELDSERVINGS))
                val recIngredient = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_INGREDIENTLINES))
                val recCalories = cursor.getFloat(cursor.getColumnIndex(COLUMN_RECIPE_CALORIES))
                val recTime = cursor.getFloat(cursor.getColumnIndex(COLUMN_RECIPE_TOTALTIME))
                val recSource = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_SOURCE))

                val recipe = Recipe(recLabel, recImage, recUrl, recShare, recYield, recIngredient, recCalories, recTime, recSource)
                recArray.add(recipe)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return recArray
    }
    /* ---------------------------------------------------------------------- */
    companion object {
        //db version
        private const val DATABASE_VERSION = 1

        // db name
        private const val DATABASE_NAME = "recipeDB.db"

        // table names
        private const val TABLE_RECIPE = "recipe"
        private const val TABLE_MY_RECIPE = "my_recipe"
        private const val TABLE_SHOPPING_LIST = "shoppping_list"

        // common column names
        private const val KEY_ID = "_id"

        // recipe table - column names
        private const val COLUMN_RECIPE_LABEL = "recipe_label"
        private const val COLUMN_RECIPE_IMAGE = "recipe_image"
        private const val COLUMN_RECIPE_URL = "recipe_url"
        private const val COLUMN_RECIPE_SHARE = "recipe_share"
        private const val COLUMN_RECIPE_YIELDSERVINGS = "recipe_yield_servings"
        private const val COLUMN_RECIPE_INGREDIENTLINES = "recipe_ingredient_lines"
        private const val COLUMN_RECIPE_CALORIES = "recipe_calories"
        private const val COLUMN_RECIPE_TOTALTIME = "recipe_total_time"
        private const val COLUMN_RECIPE_SOURCE = "source"

        // shopping list - column names
        private const val COLUMN_SHOP_NAME = "shopping_name"
        private const val COLUMN_SHOP_RECIPE = "shopping_recipe"

        //  recipe
        private const val CREATE_RECIPE_TABLE = ("CREATE TABLE " + TABLE_RECIPE
                + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_RECIPE_LABEL + " TEXT, "
                + COLUMN_RECIPE_IMAGE + " TEXT, "
                + COLUMN_RECIPE_URL + " TEXT, "
                + COLUMN_RECIPE_SHARE + " TEXT, "
                + COLUMN_RECIPE_YIELDSERVINGS + " FLOAT, "
                + COLUMN_RECIPE_INGREDIENTLINES + " TEXT, "
                + COLUMN_RECIPE_CALORIES + " FLOAT, "
                + COLUMN_RECIPE_TOTALTIME + " FLOAT, "
                + COLUMN_RECIPE_SOURCE + " STRING" +  ");")

        //  my recipe
        private const val CREATE_MY_RECIPE_TABLE = ("CREATE TABLE " + TABLE_MY_RECIPE
                + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_RECIPE_LABEL + " TEXT, "
                + COLUMN_RECIPE_IMAGE + " TEXT, "
                + COLUMN_RECIPE_URL + " TEXT, "
                + COLUMN_RECIPE_SHARE + " TEXT, "
                + COLUMN_RECIPE_YIELDSERVINGS + " FLOAT, "
                + COLUMN_RECIPE_INGREDIENTLINES + " TEXT, "
                + COLUMN_RECIPE_CALORIES + " FLOAT, "
                + COLUMN_RECIPE_TOTALTIME + " FLOAT, "
                + COLUMN_RECIPE_SOURCE + " STRING" +  ");")

        //  shopping list
        private const val CREATE_TABLE_SHOPPING_LIST = ("CREATE TABLE " + TABLE_SHOPPING_LIST
                + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_SHOP_NAME + " TEXT, "
                + COLUMN_SHOP_RECIPE + " TEXT" +  ");")
    }

}