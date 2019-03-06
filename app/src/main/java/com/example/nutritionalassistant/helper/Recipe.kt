package com.example.nutritionalassistant.helper

import android.os.Parcel
import android.os.Parcelable

// Object for recipes
// Currently only has basic information, will have more later

data class Recipe(val label: String, val image: String, val source: String,
                  val url: String, val shareAs: String, val yeild: Float,
                  val ingredientsLines: String, val calories: Float, val totalTime: Float) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readFloat(),
            parcel.readString(),
            parcel.readFloat(),
            parcel.readFloat()) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(label)
        parcel.writeString(image)
        parcel.writeString(source)
        parcel.writeString(url)
        parcel.writeString(shareAs)
        parcel.writeFloat(yeild)
        parcel.writeString(ingredientsLines)
        parcel.writeFloat(calories)
        parcel.writeFloat(totalTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}