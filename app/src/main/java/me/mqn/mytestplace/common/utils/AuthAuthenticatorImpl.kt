package me.mqn.mytestplace.common.utils

import kotlinx.coroutines.runBlocking
import me.mqn.mytestplace.data.datastore.TokenManager
import me.mqn.mytestplace.data.dto.LoginResponse
import me.mqn.mytestplace.data.service.ApiService
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AuthAuthenticatorImpl @Inject constructor(private val tokenManager: TokenManager) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            // tokenManager.getToken().first()
            tokenManager.getAccessJwt()
        }
        return runBlocking {
            val newToken = getNewToken(refreshToken = token)

            if (!newToken.isSuccessful || newToken.body() == null) {
                // Couldn't refresh the token, so restart the login process
                tokenManager.clearAllTokens()
            }

            newToken.body()?.let {
                tokenManager.saveAccessJwt(it.token)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.token}")
                    .build()
            }
        }
    }

    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<LoginResponse> {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jwt-test-api.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val service = retrofit.create(ApiService::class.java)

        return service.refreshToken("Bearer $refreshToken")
    }
}