package com.example.mealmate.repository
import com.example.mealmate.data.api.methods.PropositionApi
import com.example.mealmate.data.api.methods.PropositionsApi
import com.example.mealmate.data.api.request.GetRestaurantMapsRequest
import com.example.mealmate.data.api.request.PropostionRequest
import com.example.mealmate.data.api.response.GetRestaurantMapsResponse
import com.example.mealmate.data.api.response.PropositionResponse
import retrofit2.Response

class PropositionRepository {
    suspend fun AddProposition(propostionRequest: PropostionRequest): Response<PropositionResponse>? {
        return  PropositionApi.getApi()?.AddProposition(propostionRequest = propostionRequest)

    
    }
    suspend fun GetRestaurantsMaps(getRestaurantMapsRequest: GetRestaurantMapsRequest): Response<GetRestaurantMapsResponse>? {
        return  PropositionsApi.getApi()?.getRestaurants(getRestaurantMapsRequest = getRestaurantMapsRequest)
    }
}

