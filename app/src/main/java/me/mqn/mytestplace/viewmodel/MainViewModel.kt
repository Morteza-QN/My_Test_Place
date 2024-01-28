package me.mqn.mytestplace.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import me.mqn.mytestplace.common.base.BaseViewModel
import me.mqn.mytestplace.data.dto.ApiResponse
import me.mqn.mytestplace.data.dto.UserInfoResponse
import me.mqn.mytestplace.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
) : BaseViewModel() {

    private val _userInfoResponse = MutableLiveData<ApiResponse<UserInfoResponse>>()
    val userInfoResponse = _userInfoResponse

    // fun getUserInfo(coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
    //     _userInfoResponse,
    //     coroutinesErrorHandler,
    // ) {
    //     // repository.getUserInfo()
    // }
}