package me.mqn.mytestplace.data.service

import me.mqn.mytestplace.data.dto.Auth
import me.mqn.mytestplace.data.dto.LoginResponse
import me.mqn.mytestplace.data.dto.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body auth: Auth): Response<LoginResponse>

    @GET("auth/refresh")
    suspend fun refreshToken(@Header("Authorization") token: String): Response<LoginResponse>

    @GET("user/info")
    suspend fun getUserInfo(): Response<UserInfoResponse>
}