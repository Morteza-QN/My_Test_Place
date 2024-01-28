package me.mqn.mytestplace.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.mqn.mytestplace.data.source.remote.RemoteDataSource
import me.mqn.mytestplace.data.source.remote.RemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @[Binds Singleton]
    abstract fun bindDataSource(dataSource: RemoteDataSourceImpl): RemoteDataSource
}