package com.carterchen247.breakingbadapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("characters/{id}")
    fun getCharacterInfo(@Path("id") id: Int): Call<List<CharacterInfo>>
}