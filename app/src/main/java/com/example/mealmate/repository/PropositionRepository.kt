package com.example.mealmate.repository

import com.example.mealmate.data.api.methods.PropositionsApi
import com.example.mealmate.data.api.request.GetRestaurantMapsRequest
import com.example.mealmate.data.api.response.GetRestaurantMapsResponse
import retrofit2.Response

class PropositionRepository {

    suspend fun GetRestaurantsMaps(getRestaurantMapsRequest: GetRestaurantMapsRequest): Response<GetRestaurantMapsResponse>? {
        return  PropositionsApi.getApi()?.getRestaurants(getRestaurantMapsRequest = getRestaurantMapsRequest)
    }
}