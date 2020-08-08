package com.carterchen247.breakingbadapp

import retrofit2.Call

class RemoteDataSource(private val apiService: ApiService) {
    fun getCharacterInfo(id: Int): Call<List<CharacterInfo>> {
        return apiService.getCharacterInfo(id)
    }
}
