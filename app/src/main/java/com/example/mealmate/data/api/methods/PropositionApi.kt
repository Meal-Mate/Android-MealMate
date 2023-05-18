package com.example.mealmate.data.api.methods

import com.example.mealmate.data.api.ApiClient
import com.example.mealmate.data.api.request.PropostionRequest
import com.example.mealmate.data.api.response.PropositionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PropositionApi {
    @POST("/proposition/add")
    suspend fun AddProposition(@Body propostionRequest: PropostionRequest): Response<PropositionResponse>

    companion object {
        fun getApi(): PropositionApi? {
            return ApiClient.client?.create(PropositionApi::class.java)
        }
    }
}