package com.example.mealmate.repository

import com.example.mealmate.data.api.methods.UserApi
import com.example.mealmate.data.api.request.*
import com.example.mealmate.data.api.response.*
import retrofit2.Response

class UserRepository {

   suspend fun loginUser(loginRequest:LoginRequest): Response<LoginResponse>? {
      return  UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
    suspend fun registerUser(registerRequest:RegisterRequest): Response<RegisterResponse>? {
      return  UserApi.getApi()?.registerUser(registerRequest = registerRequest)
    }
    suspend fun recoverPassword(recoverPasswordRequest: RecoverPasswordRequest):Response<RecoverPasswordResponse>?{
        return UserApi.getApi()?.recover(recoverPasswordRequest=recoverPasswordRequest)
    }
    suspend fun findByEmail(findEmailRequest: FindEmailRequest):Response<FindEmailResponse>?{
        return UserApi.getApi()?.findByEmail(findEmailRequest = findEmailRequest)
    }
    suspend fun reset(resetRequest: ResetRequest):Response<ResetResponse>?{
        return UserApi.getApi()?.reset(resetRequest=resetRequest)
    }
}