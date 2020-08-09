package com.carterchen247.breakingbadapp

class RemoteDataSource(
    private val apiService: ApiService
) {
    suspend fun getCharactersInfo(): Result<List<CharacterInfo>> {
        return try {
            Result.success(apiService.getCharactersInfo())
        } catch (throwable: Throwable) {
            Result.failure(throwable)
        }
    }
}
