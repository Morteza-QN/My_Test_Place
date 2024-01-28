package me.mqn.mytestplace.data.repository

import kotlinx.coroutines.flow.Flow
import me.mqn.mytestplace.data.dto.ApiResponse
import me.mqn.mytestplace.data.dto.Auth
import me.mqn.mytestplace.data.dto.LoginResponse
import me.mqn.mytestplace.data.source.remote.RemoteDataSource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remote: RemoteDataSource) : Repository {

    override fun login(auth: Auth): Flow<ApiResponse<LoginResponse>> = remote.login(auth)
}