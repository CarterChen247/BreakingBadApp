package com.carterchen247.breakingbadapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val appContainer by lazy { (application as App).appContainer }
    private val remoteDataSource by lazy { appContainer.remoteDataSource }
    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(viewModelStore, CharacterViewModelFactory(remoteDataSource))
            .get(CharacterViewModel::class.java)
    }
    private lateinit var charactersInfoAdapter: CharactersInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        viewModel.characterInfoEvent.observe(this, Observer { charactersInfo ->
            Timber.d("charactersInfo=$charactersInfo")
            charactersInfoAdapter.updateData(charactersInfo)
        })

        viewModel.errorEvent.observe(this, Observer { throwable ->
            showToast("$throwable")
        })

        btnGetCharacterInfo.setOnClickListener {
            viewModel.getCharacterInfo()
        }
    }

    private fun initViews() {
        charactersInfoAdapter =
            CharactersInfoAdapter(object : CharactersInfoAdapter.ItemClickListener {
                override fun onItemClicked(characterInfo: CharacterInfo) {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            CharacterInfoActivity::class.java
                        ).apply {
                            putExtra(CharacterInfoActivity.CHARACTER_INFO, characterInfo)
                        })
                }
            })
        recyclerView.run {
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            addItemDecoration(CharacterItemDecoration(resources.getDimension(R.dimen.margin_top_characters_info_list)))
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = charactersInfoAdapter
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}