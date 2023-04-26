package com.example.mealmate.data.api.request

import com.google.gson.annotations.SerializedName

data class FindEmailRequest (
    @SerializedName("email")
    var email:String
        )