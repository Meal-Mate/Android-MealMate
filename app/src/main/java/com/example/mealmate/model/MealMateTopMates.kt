package com.example.mealmate.model


data class MealMateTopMates (
    val imageItem: String,
    val itemName: String,
    val itemFollow: Int,
    val isVerified: Boolean,
    var isFollowed: Boolean
)