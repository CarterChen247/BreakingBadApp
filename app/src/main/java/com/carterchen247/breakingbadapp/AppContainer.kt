package com.carterchen247.breakingbadapp

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber

class AppContainer {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("https://www.breakingbadapi.com/api/")
        .build()
    private val apiService = retrofit.create(ApiService::class.java)
    val remoteDataSource = RemoteDataSource(apiService)
    init {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}