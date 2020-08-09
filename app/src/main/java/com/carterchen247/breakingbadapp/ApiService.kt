package com.carterchen247.breakingbadapp

import retrofit2.http.GET

interface ApiService {
    @GET("characters")
    suspend fun getCharactersInfo(): List<CharacterInfo>
}