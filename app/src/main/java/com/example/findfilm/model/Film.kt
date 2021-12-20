package com.example.findfilm.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//пришлость в gradle файле добавлять экспериментальные плагины для аннотации Parcelize а именно
/*apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'*/

@Parcelize
data class Film(val title: String, val poster: Int, val description: String, var isInFavorites: Boolean = false):Parcelable
