package com.example.mealmate.data.api.methods

import com.example.mealmate.data.api.ApiClient
import com.example.mealmate.data.api.request.LoginRequest
import com.example.mealmate.data.api.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/user/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}