package com.example.nutritionalassistant.helper

class ShopItem {

    var id: Int? = 0
    var name: String? = null
    var recipe: String? = null
    var price: Double? = 0.0
    var calories: Int? = 0
    var quantity: Int? = 0

    constructor(id: Int, name: String, recipe: String, price: Double, calories: Int, quantity: Int) {
        this.id = id
        this.name = name
        this.recipe = recipe
        this.price = price
        this.calories = calories
    }

    constructor(name: String, recipe: String, price: Double, calories: Int, quantity: Int){
        this.name = name
        this.recipe = recipe
        this.price = price
        this.calories = calories
        this.quantity = quantity
    }
}