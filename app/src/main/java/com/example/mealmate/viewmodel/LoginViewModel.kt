package com.harshita.retrofitlogin.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.mealmate.data.api.request.LoginRequest
import com.example.mealmate.data.api.request.RecoverPasswordRequest
import com.example.mealmate.data.api.request.RegisterRequest
import com.example.mealmate.data.api.response.BaseResponse
import com.example.mealmate.data.api.response.LoginResponse
import com.example.mealmate.data.api.response.RecoverPasswordResponse
import com.example.mealmate.data.api.response.RegisterResponse
import com.example.mealmate.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()
    val registerResult: MutableLiveData<BaseResponse<RegisterResponse>> = MutableLiveData()
    val recoverResult:MutableLiveData<BaseResponse<RecoverPasswordResponse>> = MutableLiveData()

    fun loginUser(email: String, pwd: String) {

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    loginResult.value = BaseResponse.Success(response.body())
                } else {
                    loginResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
    fun registerUser(email: String, password: String,username:String,phone:String) {

        registerResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val registerRequest = RegisterRequest(
                    username=username,
                    email = email,
                    password = password,
                    phone=phone

                )
                val response = userRepo.registerUser(registerRequest = registerRequest)
                if (response?.code() == 200) {
                    registerResult.value = BaseResponse.Success(response.body())
                } else {
                    registerResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                registerResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
    fun recoverPassword(email: String) {
        println("Recovering password for email: $email")
        recoverResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val recoverPasswordRequest = RecoverPasswordRequest(
                    email = email

                )
                val response = userRepo.recoverPassword(recoverPasswordRequest = recoverPasswordRequest)
                if (response?.code() == 200) {
                    recoverResult.value = BaseResponse.Success(response.body())
                } else {
                    registerResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                recoverResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}