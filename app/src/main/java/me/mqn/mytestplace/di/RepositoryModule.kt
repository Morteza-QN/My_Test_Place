package me.mqn.mytestplace.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.mqn.mytestplace.data.repository.Repository
import me.mqn.mytestplace.data.repository.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @[Binds Singleton]
    abstract fun bindsRepository(repository: RepositoryImpl): Repository
}