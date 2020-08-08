package com.carterchen247.breakingbadapp

class RemoteDataSource(
    private val apiService: ApiService
) {
    suspend fun getCharactersInfo(id: Int): Result<List<CharacterInfo>> {
        return try {
            Result.success(apiService.getCharactersInfo(id))
        } catch (throwable: Throwable) {
            Result.failure(throwable)
        }
    }
}
