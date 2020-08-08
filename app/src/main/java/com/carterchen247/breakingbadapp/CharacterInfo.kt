package com.carterchen247.breakingbadapp

data class CharacterInfo(
    val char_id: Int,
    val name: String,
    val birthday: String,
    val occupation: List<String>,
    val img: String,
    val nickname: String,
    val appearance: List<Int>,
    val portrayed: String
)