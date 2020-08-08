package com.carterchen247.breakingbadapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val appContainer by lazy { (application as App).appContainer }
    private val remoteDataSource by lazy { appContainer.remoteDataSource }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGetCharacterInfo.setOnClickListener {
            appContainer.applicationScope.launch {
                remoteDataSource.getCharactersInfo(1).fold({ charactersInfo ->
                    Timber.d("getCharactersInfo success. $charactersInfo")
                }, { t ->
                    Timber.d("getCharactersInfo failed. $t")
                })
            }
        }
    }
}