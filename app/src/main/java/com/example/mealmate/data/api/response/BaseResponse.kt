package com.example.mealmate.data.api.response

sealed class BaseResponse<out T> {
    data class Success<out T>(val data: T? = null) : BaseResponse<T>()
    data class List<out T>(val data: List<T>? = null) : BaseResponse<T>()
    data class ListSuccess<out T>(val data: List<T>?) : BaseResponse<T>()
    data class Loading(val nothing: Nothing?=null) : BaseResponse<Nothing>()
    data class Error(val msg: String?) : BaseResponse<Nothing>()
}