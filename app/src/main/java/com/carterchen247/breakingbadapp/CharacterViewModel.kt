package com.carterchen247.breakingbadapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber

class CharacterViewModel(private val remoteDataSource: RemoteDataSource) : ViewModel() {

    val characterInfoEvent = MutableLiveData<CharacterInfo>()

    fun getCharacterInfo(id: Int) {
        viewModelScope.launch {
            remoteDataSource.getCharactersInfo(id).fold({ charactersInfo ->
                Timber.d("getCharactersInfo success. $charactersInfo")
                charactersInfo.first().let {
                    characterInfoEvent.value = it
                }
            }, { t ->
                Timber.d("getCharactersInfo failed. $t")
            })
        }
    }

}