package com.example.mealmate.data.api.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest (
    @SerializedName("username")
    var username: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password:String,
    @SerializedName("phone")
    var phone: String
)