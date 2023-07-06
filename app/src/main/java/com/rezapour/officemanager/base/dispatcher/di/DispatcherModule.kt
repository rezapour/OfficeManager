package com.rezapour.officemanager.base.dispatcher.di

import com.rezapour.officemanager.base.dispatcher.DispatcherProvider
import com.rezapour.officemanager.base.dispatcher.impl.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DispatcherModule {

    @Binds
    fun bindDispatcherProvider(impl: DispatcherProviderImpl): DispatcherProvider
}