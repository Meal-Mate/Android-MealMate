package com.example.mealmate.data.api.response

import com.google.gson.annotations.SerializedName


data class PropositionResponse(
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("user")
    var `data`: Data,
) {
    data class Data(
        @SerializedName("owner")
        var owner: String,
        @SerializedName("propostiondDate")
        var propostiondDate: String,
        @SerializedName("restaurantName")
        var restaurantName: String,
        @SerializedName("restaurantAddress")
        var restaurantAddress: String
    )
}