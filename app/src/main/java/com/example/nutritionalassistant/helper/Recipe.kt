package com.example.nutritionalassistant.helper

import android.os.Parcel
import android.os.Parcelable

// Object for recipes
class Recipe{

    var id: Int? = 0
    var label: String? = null
    var image: String? = null
    var url: String? = null
    var shareAs: String? = null
    var yieldServings: Float? = 0.0f
    var ingredientsLines: String? =null
    var calories: Float? = 0.0F
    var totalTime: Float? = 0.0F
    var source: String? = null

//    constructor(id: Int, label: String, image: String, url: String, shareAs: String,
//                yieldServings: Float, ingredientsLines: String?,
//                calories: Float, totalTime: Float) {
//        this.id = id
//        this.label = label
//        this.image = image
//        this.url = url
//        this.shareAs = shareAs
//        this.yieldServings = yieldServings
//        this.ingredientsLines = ingredientsLines
//        this.calories = calories
//        this.totalTime = totalTime
//    }

    constructor(label: String, image: String, url: String, shareAs: String,
                yieldServings: Float, ingredientsLines: String?,
                calories: Float, totalTime: Float, source: String) {
        this.label = label
        this.image = image
        this.url = url
        this.shareAs = shareAs
        this.yieldServings = yieldServings
        this.ingredientsLines = ingredientsLines
        this.calories = calories
        this.totalTime = totalTime
        this.source = source
    }
}