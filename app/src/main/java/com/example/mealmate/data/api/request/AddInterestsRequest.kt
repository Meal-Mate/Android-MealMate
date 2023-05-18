package com.example.mealmate.data.api.request

import com.google.gson.annotations.SerializedName
import retrofit2.http.Header

data class AddInterestsRequest(
    @Header("Authorization")
    val token: String,
    @SerializedName("interest")
    var interest: String
)