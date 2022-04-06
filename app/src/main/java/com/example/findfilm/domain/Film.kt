package com.example.findfilm.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//пришлость в gradle файле добавлять экспериментальные плагины для аннотации Parcelize а именно
/*apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'*/

@Parcelize
data class Film(
    val title: String,
    val poster: String, //У нас будут приходить ссылки на картинку, так что это будет String
    val description: String,
    var rating: Double = 0.0, //Приходит не целое число
    var isInFavorites: Boolean = false
) : Parcelable
