package me.mqn.mytestplace.data.source

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull
import me.mqn.mytestplace.data.dto.ApiResponse
import me.mqn.mytestplace.data.dto.ErrorResponse
import retrofit2.Response

abstract class BaseDataSource {

    fun <T> apiRequestFlow(call: suspend () -> Response<T>): Flow<ApiResponse<T>> = flow {
        emit(ApiResponse.Loading)

        withTimeoutOrNull(20000L) {
            val response = call.invoke()

            try {
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        emit(ApiResponse.Success(data = data))
                    }
                } else {
                    response.errorBody()?.let { error ->
                        error.close()
                        val parsedError: ErrorResponse =
                            Gson().fromJson(error.charStream(), ErrorResponse::class.java)
                        emit(ApiResponse.Failure(errorMessage = parsedError.message, code = parsedError.code))
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Failure(e.message ?: e.toString(), 4000))
            }
        } ?: emit(ApiResponse.Failure("Timeout! Please try again.", 4008))
    }.flowOn(Dispatchers.IO).catch {
        emit(ApiResponse.Failure(it.message ?: it.toString(), 4000))
    }
}