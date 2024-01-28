package me.mqn.mytestplace.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.mqn.mytestplace.BuildConfig
import me.mqn.mytestplace.common.utils.AuthAuthenticatorImpl
import me.mqn.mytestplace.common.utils.AuthInterceptorImpl
import me.mqn.mytestplace.common.utils.NetworkConfig
import me.mqn.mytestplace.data.datastore.TokenManager
import me.mqn.mytestplace.data.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @[Provides Singleton Named("baseUrl")]
    fun provideBaseUrl() = NetworkConfig.BASE_URL

    @[Provides Singleton]
    fun provideConnectionTimeout() = NetworkConfig.CONNECTION_TIMEOUT

    @[Provides Singleton]
    fun provideGsonLenient(): Gson = GsonBuilder().setLenient().create()

    @[Provides Singleton]
    fun provideConvertor(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @[Provides Singleton]
    fun provideAuthInterceptor(tokenManager: TokenManager) = AuthInterceptorImpl(tokenManager)

    @[Provides Singleton]
    fun provideAuthAuthenticator(tokenManager: TokenManager) = AuthAuthenticatorImpl(tokenManager)

    @[Provides Singleton]
    fun provideBodyInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @[Provides Singleton]
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptorImpl,
        authAuthenticator: AuthAuthenticatorImpl,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .authenticator(authAuthenticator)
        .addInterceptor(authInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @[Provides Singleton]
    fun provideRetrofitBuilder(
        @Named("baseUrl") baseUrl: String,
        converter: Converter.Factory,
        okHttpClient: OkHttpClient,
    ): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(converter)

    @[Provides Singleton]
    fun provideAPIService(retrofit: Retrofit.Builder): ApiService =
        retrofit.build().create(ApiService::class.java)
}