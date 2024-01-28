package me.mqn.mytestplace.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.mqn.mytestplace.data.datastore.TokenManager
import javax.inject.Inject

@HiltViewModel
class TokenViewModel @Inject constructor(private val tokenManager: TokenManager) : ViewModel() {

    val token = MutableLiveData<String?>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            // tokenManager.getToken().collect {
            //     withContext(Dispatchers.Main) {
            //         token.value = it
            //     }
            // }
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // tokenManager.saveToken(token)
        }
    }

    fun deleteToken() {
        viewModelScope.launch(Dispatchers.IO) {
            // tokenManager.deleteToken()
        }
    }
}