package com.carterchen247.breakingbadapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CharacterViewModelFactory(
    private val remoteDataSource: RemoteDataSource
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass != CharacterViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return CharacterViewModel(remoteDataSource) as T
    }
}