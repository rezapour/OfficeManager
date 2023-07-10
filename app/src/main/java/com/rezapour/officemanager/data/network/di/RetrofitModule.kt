package com.rezapour.officemanager.data.network.di

import com.rezapour.officemanager.data.Constants
import com.rezapour.officemanager.data.network.retrofit.ApiService
import com.rezapour.officemanager.data.network.retrofit.client.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(): Retrofit {
        return RetrofitClient().retrofitProvider(Constants.BASE_URL, Constants.TIME_OUT)
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}