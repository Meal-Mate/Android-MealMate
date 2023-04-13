package com.example.mealmate.model

data class MealMateTopRestaurants (
    val itemName: String,
    val numberItem: Int,
    val imageItem: String,
    val floorPrice: Double,
    val volumeTrade: String,
    val isSurplus: Boolean = false,
    val percent: String,
    val verified: Boolean
)