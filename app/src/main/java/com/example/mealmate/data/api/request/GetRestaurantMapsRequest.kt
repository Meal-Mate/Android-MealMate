package com.example.mealmate.data.api.request

import com.google.gson.annotations.SerializedName

data class GetRestaurantMapsRequest (
@SerializedName("long")
var long:String,
@SerializedName("lat")
var lat:String,
@SerializedName("keyword")
var keyword:String,
@SerializedName("radius")
var radius:String
)
