package me.mqn.mytestplace.data.source.remote

import kotlinx.coroutines.flow.Flow
import me.mqn.mytestplace.data.dto.ApiResponse
import me.mqn.mytestplace.data.dto.Auth
import me.mqn.mytestplace.data.dto.LoginResponse

interface RemoteDataSource {

    fun login(auth: Auth): Flow<ApiResponse<LoginResponse>>
}