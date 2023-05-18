package com.example.mealmate.data.api.request

import com.google.gson.annotations.SerializedName

data class PropostionRequest(
    @SerializedName("owner")
    var owner: String,
    @SerializedName("restaurantName")
    var restaurantName: String,
    @SerializedName("date")
    var date: String,
    @SerializedName("restaurantAddress")
    var restaurantAddress: String
)