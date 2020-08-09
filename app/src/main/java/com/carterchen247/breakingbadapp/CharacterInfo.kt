package com.carterchen247.breakingbadapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterInfo(
    val char_id: Int,
    val name: String,
    val birthday: String,
    val occupation: List<String>,
    val img: String,
    val nickname: String,
    val appearance: List<Int>,
    val portrayed: String
) : Parcelable