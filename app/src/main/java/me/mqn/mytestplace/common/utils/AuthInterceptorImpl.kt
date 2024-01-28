package me.mqn.mytestplace.common.utils

import kotlinx.coroutines.runBlocking
import me.mqn.mytestplace.data.datastore.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import okio.AsyncTimeout.Companion.lock
import java.net.HttpURLConnection
import javax.inject.Inject

class AuthInterceptorImpl @Inject constructor(private val tokenManager: TokenManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getAccessJwt()
        }
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer $token")
        val response = chain.proceed(request.build())


        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            synchronized(this) {
                lock.lock()
                response.request.newBuilder().delete()
                runBlocking {
                    tokenManager.clearAllTokens()
                }
                // val intent = Intent(context, MainActivity::class.java)
                // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                // context.startActivity(intent)

                lock.unlock()
            }
        }


        return response
    }
}