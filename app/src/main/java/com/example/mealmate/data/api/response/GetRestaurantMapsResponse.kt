package com.example.mealmate.data.api.response

import com.google.gson.annotations.SerializedName

data class GetRestaurantMapsResponse (
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("places")
    var data: List<Data>,
    @SerializedName("message")
    var message: String
) {
    data class Data(
        @SerializedName("lat")
        var lat: String,
        @SerializedName("long")
        var long: String,
        @SerializedName("title")
        var title: String,
        @SerializedName("id")
        var id: String
    )
}