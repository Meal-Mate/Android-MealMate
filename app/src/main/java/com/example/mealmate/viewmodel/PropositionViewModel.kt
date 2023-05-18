package com.example.mealmate.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mealmate.data.api.request.GetRestaurantMapsRequest
import com.example.mealmate.data.api.request.LoginRequest
import com.example.mealmate.data.api.response.BaseResponse
import com.example.mealmate.data.api.response.GetRestaurantMapsResponse
import com.example.mealmate.data.api.response.LoginResponse
import com.example.mealmate.repository.PropositionRepository
import kotlinx.coroutines.launch

class PropositionViewModel(application: Application) : AndroidViewModel(application)  {
    val propositonRepo= PropositionRepository()
    val getRestaurantResult: MutableLiveData<BaseResponse<GetRestaurantMapsResponse>> = MutableLiveData()


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