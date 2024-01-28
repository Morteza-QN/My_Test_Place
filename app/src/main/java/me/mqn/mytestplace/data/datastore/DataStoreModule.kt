package me.mqn.mytestplace.data.datastore

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @[Provides Singleton]
    fun provideJwtTokenManager(
        @ApplicationContext context: Context
    ): TokenManager = TokenManagerImpl(context)
}