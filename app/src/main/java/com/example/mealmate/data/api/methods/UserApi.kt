package com.example.mealmate.data.api.methods

import com.example.mealmate.data.api.ApiClient
import com.example.mealmate.data.api.request.*
import com.example.mealmate.data.api.response.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @POST("/user/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/user/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>

    @POST("/resetpassword/recover")
    suspend fun recover(@Body recoverPasswordRequest: RecoverPasswordRequest):Response<RecoverPasswordResponse>

    @POST("/resetpassword/findByEmail")
    suspend fun findByEmail(@Body findEmailRequest: FindEmailRequest):Response<FindEmailResponse>

    @GET("/resetpassword/reset")
    suspend fun reset(@Body resetRequest: ResetRequest):Response<ResetResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}