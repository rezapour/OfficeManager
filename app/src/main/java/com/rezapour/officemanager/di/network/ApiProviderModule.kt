package com.rezapour.officemanager.di.network

import com.rezapour.officemanager.data.network.ApiProvider
import com.rezapour.officemanager.data.network.impl.ApiProviderImpl
import com.rezapour.officemanager.data.network.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiProviderModule {
    @Singleton
    @Provides
    fun provideNetworkDataProvider(
        apiService: ApiService
    ): ApiProvider = ApiProviderImpl(apiService)
}