package com.example.mealmate.data.api.response

import com.google.gson.annotations.SerializedName

data class FindEmailResponse (
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("user")
    var `data`: Data,
    @SerializedName("message")
    var message: String
) {
    data class Data(
        @SerializedName("_id")
        var id: String,
        @SerializedName("username")
        var username: String,
        @SerializedName("email")
        var email: String,
        @SerializedName("phone")
        var phone: String
    )
}