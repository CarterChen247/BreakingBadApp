package com.carterchen247.breakingbadapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val remoteDataSource by lazy { (application as App).appContainer.remoteDataSource }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGetCharacterInfo.setOnClickListener {
            remoteDataSource.getCharacterInfo(1).enqueue(object : Callback<List<CharacterInfo>> {
                override fun onFailure(call: Call<List<CharacterInfo>>, t: Throwable) {
                    Timber.d("get character info onFailure")
                }

                override fun onResponse(
                    call: Call<List<CharacterInfo>>,
                    response: Response<List<CharacterInfo>>
                ) {
                    Timber.d("get character info onResponse")
                }
            })
            Timber.d("get character info")
        }
    }
}