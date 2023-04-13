package com.example.mealmate.data.api.methods

import com.example.mealmate.data.api.ApiClient
import com.example.mealmate.data.api.request.LoginRequest
import com.example.mealmate.data.api.request.RecoverPasswordRequest
import com.example.mealmate.data.api.request.RegisterRequest
import com.example.mealmate.data.api.response.LoginResponse
import com.example.mealmate.data.api.response.RecoverPasswordResponse
import com.example.mealmate.data.api.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/user/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/user/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>

    @POST("/resetpassword/recover")
    suspend fun recover(@Body recoverPasswordRequest: RecoverPasswordRequest):Response<RecoverPasswordResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}