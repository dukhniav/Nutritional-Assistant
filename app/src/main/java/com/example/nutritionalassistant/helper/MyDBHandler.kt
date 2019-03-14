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
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // on upgrade, drop old tables
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_RECIPE")
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

        Log.d("TAG", "Before WritableDatabase")
        val db = this.writableDatabase
        Log.d("TAG", "WritableDatabase")

        db.insert(TABLE_RECIPE, null, values)
        db.close()
        Log.d("TAG", "works")
    }

    fun findRecipe(recipeLabel: String): Recipe? {
        val query = "SELECT * FROM $TABLE_RECIPE WHERE $COLUMN_RECIPE_LABEL = \"$recipeLabel\""

        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null)

        var recipe: Recipe? = null

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val id = Integer.parseInt(cursor.getString(0))
            val label = cursor.getString(1)
            val image = cursor.getString(2)
            val url = cursor.getString(3)
            val shareAs = cursor.getString(4)
            val yieldServings = cursor.getString(5).toFloat()
            val ingredients = cursor.getString(6)
            val calories = cursor.getString(7).toFloat()
            val totalTime = cursor.getString(8).toFloat()

            recipe = Recipe(id, label, image,
                        url, shareAs, yieldServings, ingredients,
                        calories, totalTime)


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
                val recipe = Recipe(recLabel, recImage, recUrl, recShare, recYield, recIngredient, recCalories, recTime)
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
//
//    fun addIngredients(url: String, ingredients: ArrayList<String>) {
//        val values = ContentValues()
//        for (i in 0 until ingredients.size) {
//            values.put(COLUMN_ING_SOURCE, url)
//            values.put(COLUMN_INGREDIENT, ingredients[i])
//        }
//
//        val db = this.writableDatabase
//
//        db.insert(TABLE_INGREDIENTS, null, values)
//        db.close()
//    }
//
//    fun findIngredient(url: String): ArrayList<String> {
//
//        val query = "SELECT *TABLE_$TABLE_INGREDIENTS WHERE $COLUMN_ING_SOURCE = \"$url\""
//
//        val db = this.writableDatabase
//
//        val cursor = db.rawQuery(query, null)
//
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst()
//
//            val id = Integer.parseInt(cursor.getString(0))
//            val url = cursor.getString(1)
//            val ingredient = cursor.getString(2)
//
//            cursor.close()
//        }
//        db.close()
//        return recipe
//
//
//    }
    // ----------------------------- SAVE --------------------------------------
//    fun savePref(cook: Int, budget: Int, dif: Int, serv: Int) {
//        val values = ContentValues()
//        values.put(COLUMN_COOK_TIME, cook)
//        values.put(COLUMN_BUDGET, budget)
//        values.put(COLUMN_DIFFICULTY, dif)
//        values.put(COLUMN_SERVINGS, serv)
//
//        val db = this.writableDatabase
//
//        db.insert(TABLE_SAVE, null, values)
//        db.close()
//    }
//    fun getSave(): ArrayList<Int>? {
//        val query = "SELECT * FROM $TABLE_SAVE"
//
//        val db = this.writableDatabase
//
//        val save:ArrayList<Int> = ArrayList()
//
//        val cursor = db.rawQuery(query, null)
//
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst()
//
//            val cook = Integer.parseInt(cursor.getString(0))
//            val budget = Integer.parseInt(cursor.getString(1))
//            val dif = Integer.parseInt(cursor.getString(2))
//            val serv = Integer.parseInt(cursor.getString(3))
//
//            save.add(cook)
//            save.add(budget)
//            save.add(dif)
//            save.add(serv)
//
//            cursor.close()
//        }
//        db.close()
//        return save
//    }
//    fun deleteSave() {
//        val db = this.writableDatabase
//        db.execSQL("DROP TABLE If EXISTS $TABLE_SAVE")
//        db.close()
//    }
    /* ---------------------------------------------------------------------- */
    companion object {
        //db version
        private const val DATABASE_VERSION = 1

        // db name
        private const val DATABASE_NAME = "recipeDB.db"

        // table names
        private const val TABLE_RECIPE = "recipe"
        private const val TABLE_MY_RECIPE = "my_recipe"
        private const val TABLE_SAVE = "save"
        private const val TABLE_INGREDIENTS = "ingredients"

        // common column names
        private const val KEY_ID = "_id"
        private const val COLUMN_CREATED_AT = "created_at"

        // recipe table - column names
        private const val COLUMN_RECIPE_LABEL = "recipe_label"
        private const val COLUMN_RECIPE_IMAGE = "recipe_image"
        private const val COLUMN_RECIPE_URL = "recipe_url"
        private const val COLUMN_RECIPE_SHARE = "recipe_share"
        private const val COLUMN_RECIPE_YIELDSERVINGS = "recipe_yield_servings"
        private const val COLUMN_RECIPE_INGREDIENTLINES = "recipe_ingredient_lines"
        private const val COLUMN_RECIPE_CALORIES = "recipe_calories"
        private const val COLUMN_RECIPE_TOTALTIME = "recipe_total_time"

        // ingredients table - column names
        private const val COLUMN_ING_SOURCE = "ingredient_source"
        private const val COLUMN_INGREDIENT = "ingredient"

//        // SAVE VALS - COL NAMES
//        private const val COLUMN_SAVE_BOOL = "save_bool"
//        private const val COLUMN_COOK_TIME = "cook_time"
//        private const val COLUMN_BUDGET = "budget"
//        private const val COLUMN_DIFFICULTY = "difficulty"
//        private const val COLUMN_SERVINGS = "servings"

        // table create statements
//        //SAVE
//        private const val CREATE_SAVE_TABLE = ("CREATE TABLE " + TABLE_SAVE
//                + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
//                + COLUMN_COOK_TIME + " TEXT, "
//                + COLUMN_BUDGET + " TEXT, "
//                + COLUMN_DIFFICULTY + " TEXT, "
//                + COLUMN_SERVINGS + " TEXT" + ")")

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
                + COLUMN_RECIPE_TOTALTIME + " FLOAT" + ");")

        //  recipe
        private const val CREATE_MY_RECIPE_TABLE = ("CREATE TABLE " + TABLE_MY_RECIPE
                + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_RECIPE_LABEL + " TEXT, "
                + COLUMN_CREATED_AT + " DATETIME" + ")")


        // INGREDIENTS
        private const val CREATE_INGREDIENTS_TABLE = ("CREATE TABLE " + TABLE_INGREDIENTS
                + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_ING_SOURCE + " TEXT, "
                + COLUMN_INGREDIENT + " TEXT" + ")")
    }

}