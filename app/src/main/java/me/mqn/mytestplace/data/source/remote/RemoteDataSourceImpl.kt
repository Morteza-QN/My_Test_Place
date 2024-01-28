package me.mqn.mytestplace.data.source.remote

import kotlinx.coroutines.flow.Flow
import me.mqn.mytestplace.data.dto.ApiResponse
import me.mqn.mytestplace.data.dto.Auth
import me.mqn.mytestplace.data.dto.LoginResponse
import me.mqn.mytestplace.data.service.ApiService
import me.mqn.mytestplace.data.source.BaseDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteDataSource, BaseDataSource() {

    override fun login(auth: Auth): Flow<ApiResponse<LoginResponse>> =
        apiRequestFlow { apiService.login(auth) }
}