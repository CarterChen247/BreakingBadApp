package com.carterchen247.breakingbadapp

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("characters/{id}")
    suspend fun getCharactersInfo(@Path("id") id: Int): List<CharacterInfo>
}