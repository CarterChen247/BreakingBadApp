package com.carterchen247.breakingbadapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGetCharacterInfo.setOnClickListener {
            (application as App).appContainer.remoteDataSource.apiService.getCharacterInfo(1).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Timber.d("get character info onFailure")
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Timber.d("get character info onResponse")
                }
            })
            Timber.d("get character info")
        }
    }
}