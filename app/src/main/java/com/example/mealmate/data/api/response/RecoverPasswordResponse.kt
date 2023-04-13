package com.example.mealmate.data.api.response

import com.google.gson.annotations.SerializedName

data class RecoverPasswordResponse(
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("message")
    var message: String
)