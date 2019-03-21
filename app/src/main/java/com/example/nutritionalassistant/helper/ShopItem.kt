package com.example.nutritionalassistant.helper

import android.os.Parcel
import android.os.Parcelable

class ShopItem() : Parcelable{

    var objectId: Int? = 0
    var name: String? = null
    var recipe: String? = null

    constructor(parcel: Parcel) : this() {
        objectId = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        recipe = parcel.readString()
    }

    constructor(name: String, recipe: String) : this() {
        this.name = name
        this.recipe = recipe
    }

    constructor(objectId: Int, name: String, recipe: String, done: Boolean) : this() {
        this.objectId = objectId
        this.name = name
        this.recipe = recipe
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(objectId)
        parcel.writeString(name)
        parcel.writeString(recipe)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShopItem> {
        override fun createFromParcel(parcel: Parcel): ShopItem {
            return ShopItem(parcel)
        }

        override fun newArray(size: Int): Array<ShopItem?> {
            return arrayOfNulls(size)
        }
    }
}