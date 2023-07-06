package com.rezapour.officemanager.data.repository.di

import com.rezapour.officemanager.data.network.ApiProvider
import com.rezapour.officemanager.data.network.mapper.NetworkMapper
import com.rezapour.officemanager.data.repository.RoomRepository
import com.rezapour.officemanager.data.repository.impl.RoomRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRoomRepository(
        apiProvider: ApiProvider,
        networkMapper: NetworkMapper
    ): RoomRepository = RoomRepositoryImpl(apiProvider, networkMapper)
}