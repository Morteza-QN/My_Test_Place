package me.mqn.mytestplace.data.dto

sealed class ApiResponse<out T> {

    data object Loading : ApiResponse<Nothing>()

    data class Success<out T>(val data: T) : ApiResponse<T>()

    data class Failure(val errorMessage: String, val code: Int) : ApiResponse<Nothing>()
}