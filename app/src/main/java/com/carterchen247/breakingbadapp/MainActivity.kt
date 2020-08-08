package com.carterchen247.breakingbadapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val appContainer by lazy { (application as App).appContainer }
    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            CharacterViewModelFactory(appContainer.remoteDataSource)
        ).get(CharacterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.characterInfoEvent.observe(this, Observer { characterInfo ->
            Timber.d("characterInfo=$characterInfo")
        })

        btnGetCharacterInfo.setOnClickListener {
            viewModel.getCharacterInfo(1)
        }
    }
}