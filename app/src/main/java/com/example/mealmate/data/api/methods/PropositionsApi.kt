package com.example.mealmate.data.api.methods

import com.example.mealmate.data.api.ApiClient
import com.example.mealmate.data.api.request.GetRestaurantMapsRequest
import com.example.mealmate.data.api.request.ResetRequest
import com.example.mealmate.data.api.response.GetRestaurantMapsResponse
import com.example.mealmate.data.api.response.ResetResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface PropositionsApi {

    @GET("/places")
    suspend fun getRestaurants(@Body getRestaurantMapsRequest: GetRestaurantMapsRequest): Response<GetRestaurantMapsResponse>

    companion object {
        fun getApi(): PropositionsApi? {
            return ApiClient.client?.create(PropositionsApi::class.java)
        }
    }
}