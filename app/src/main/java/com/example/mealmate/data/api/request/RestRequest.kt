package com.example.mealmate.data.api.request

import com.google.gson.annotations.SerializedName

data class ResetRequest (
    @SerializedName("otp")
    var otp: String
)