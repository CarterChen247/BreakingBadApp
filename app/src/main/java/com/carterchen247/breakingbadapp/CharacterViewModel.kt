package com.carterchen247.breakingbadapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber

class CharacterViewModel(private val remoteDataSource: RemoteDataSource) : ViewModel() {

    val characterInfoEvent = MutableLiveData<List<CharacterInfo>>()
    val errorEvent = MutableLiveData<Throwable>()

    fun getCharacterInfo() {
        viewModelScope.launch {
            remoteDataSource.getCharactersInfo().fold({ charactersInfo ->
                val result = if (charactersInfo.isNullOrEmpty()){
                    listOf()
                }else{
                    charactersInfo
                }
                characterInfoEvent.value = result
            }, { throwable ->
                Timber.d("getCharactersInfo failed. $throwable")
                errorEvent.value = throwable
            })
        }
    }

}