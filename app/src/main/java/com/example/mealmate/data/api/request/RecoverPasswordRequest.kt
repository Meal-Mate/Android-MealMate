package com.example.mealmate.data.api.request

import com.google.gson.annotations.SerializedName

data class RecoverPasswordRequest (
    @SerializedName("email")
    var email: String
)