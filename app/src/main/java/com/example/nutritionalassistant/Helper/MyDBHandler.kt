package com.example.nutritionalassistant.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_RECIPE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // on upgrade, drop old tables
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_RECIPE")

        // create new tables
        onCreate(db)
    }

    companion object {
        //db version
        private const val DATABASE_VERSION = 1

        // db name
        private const val DATABASE_NAME = "recipeDB.db"

        // table names
        private const val TABLE_RECIPE = "recipe"
        private const val TABLE_USER = "user"

        // common column names
        private const val KEY_ID = "_id"
        private const val COLUMN_CREATED_AT = "created_at"

        // login
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_USER_PASSWORD = "user_password"

        // recipe table - column names
        private const val COLUMN_RECIPE_NAME = "recipe_name"

        // table create statements
        //  recipe
        private const val CREATE_RECIPE_TABLE = ("CREATE TABLE " + TABLE_RECIPE
                + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_RECIPE_NAME + " TEXT, "
                + COLUMN_CREATED_AT + " DATETIME" + ")")
    }
}