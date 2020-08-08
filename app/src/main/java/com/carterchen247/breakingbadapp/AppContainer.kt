package com.carterchen247.breakingbadapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class AppContainer {

    val applicationScope by lazy { CoroutineScope(SupervisorJob()) }

    private val httpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://www.breakingbadapi.com/api/")
            .client(okHttpClient)
            .build()
    }

    private val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    val remoteDataSource by lazy {
        RemoteDataSource(apiService)
    }

    init {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}