package com.example.mealmate.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mealmate.data.api.request.GetRestaurantMapsRequest
import com.example.mealmate.data.api.request.LoginRequest
import com.example.mealmate.data.api.request.PropostionRequest
import com.example.mealmate.data.api.response.BaseResponse
import com.example.mealmate.data.api.response.GetRestaurantMapsResponse
import com.example.mealmate.data.api.response.LoginResponse
import com.example.mealmate.data.api.response.PropositionResponse
import com.example.mealmate.repository.PropositionRepository
import kotlinx.coroutines.launch

class PropositionViewModel(application: Application) : AndroidViewModel(application)  {
    val propositonRepo= PropositionRepository()
    val getRestaurantResult: MutableLiveData<BaseResponse<GetRestaurantMapsResponse>> = MutableLiveData()
    val propostionResult: MutableLiveData<BaseResponse<PropositionResponse>> = MutableLiveData()

    fun AddProposition(restauName: String, date: String, address: String ,owner: String) {
        propostionResult.value = BaseResponse.Loading()
        print("was here")
        viewModelScope.launch {
            try {

                val propostionRequest = PropostionRequest(
                    owner = owner,
                    restaurantName = restauName,
                    restaurantAddress = address,
                    date=date
                )
                val response = propositonRepo.AddProposition(propostionRequest = propostionRequest)
                if (response?.code() == 200) {
                    propostionResult.value = BaseResponse.Success(response.body())
                } else {
                    propostionResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                propostionResult.value = BaseResponse.Error(ex.message)
            }
        }
    }


    fun getRestaurantsMap(long: String, lat: String,keyword:String,radius:String) {

        getRestaurantResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val getRestaurantMapsRequest=GetRestaurantMapsRequest(
                    lat=lat,
                    long = long,
                    keyword =keyword,
                    radius = radius
                )
                val response = propositonRepo.GetRestaurantsMaps(getRestaurantMapsRequest = getRestaurantMapsRequest)
                if (response?.code() == 200) {
                    getRestaurantResult.value = BaseResponse.Success(response.body())
                } else {
                    getRestaurantResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                getRestaurantResult.value = BaseResponse.Error(ex.message)
            }
        }
    }


}