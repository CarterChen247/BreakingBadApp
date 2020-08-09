package com.carterchen247.breakingbadapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_character_info.*

class CharacterInfoActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_info)
        val characterInfo = intent?.extras?.getParcelable<CharacterInfo>(CHARACTER_INFO) ?: return
        characterInfo.let {
            Glide.with(this).load(it.img).into(characterImg)
            characterName.text = it.name
        }
    }

    companion object {
        const val CHARACTER_INFO = "character_info"
    }
}