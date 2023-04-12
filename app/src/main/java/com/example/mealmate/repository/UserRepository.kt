package com.example.mealmate.repository

import com.example.mealmate.data.api.methods.UserApi
import com.example.mealmate.data.api.request.LoginRequest
import com.example.mealmate.data.api.response.LoginResponse
import retrofit2.Response

class UserRepository {

   suspend fun loginUser(loginRequest:LoginRequest): Response<LoginResponse>? {
      return  UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}