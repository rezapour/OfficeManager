package com.rezapour.officemanager.data.network.retrofit.client

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rezapour.officemanager.data.Constants
import com.rezapour.officemanager.data.network.retrofit.autenticator.BasicAuthentication
import com.rezapour.officemanager.data.network.retrofit.interceptor.HeaderInterceptor
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient() {

    fun retrofitProvider(baseUrl: String, timeOut: Long) =
        retrofitProvider(baseUrl.toHttpUrl(), timeOut)

    fun retrofitProvider(
        baseurl: HttpUrl,
        timeOut: Long
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(provideGsonConverterFactory())
            .client(provideOkhttpClient(timeOut))
            .build()
    }

    private fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(provideGsonBuilder())
    }

    private fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    private fun provideOkhttpClient(timeOut: Long): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.MILLISECONDS)
            .readTimeout(timeOut, TimeUnit.MILLISECONDS)
            .writeTimeout(timeOut, TimeUnit.MILLISECONDS)
            .addInterceptor(provideInterceptor())
            .addInterceptor(
                HeaderInterceptor(
                    BasicAuthentication.getApiKey(
                        Constants.API_USER,
                        Constants.API_PASSWORD
                    )
                )
            )
            .build()
    }

    private fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BASIC
        }
    }
}