package me.mqn.mytestplace.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import me.mqn.mytestplace.common.base.BaseViewModel
import me.mqn.mytestplace.common.base.CoroutinesErrorHandler
import me.mqn.mytestplace.data.dto.ApiResponse
import me.mqn.mytestplace.data.dto.Auth
import me.mqn.mytestplace.data.dto.LoginResponse
import me.mqn.mytestplace.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private val _loginResponse = MutableLiveData<ApiResponse<LoginResponse>>()
    val loginResponse = _loginResponse

    fun login(auth: Auth, coroutinesErrorHandler: CoroutinesErrorHandler) =
        baseRequest(_loginResponse, coroutinesErrorHandler) {
            repository.login(auth)
        }
}