package com.example.findfilm

import com.example.findfilm.data.entity.Film

class FavoritesDB {

    private val favoritesFilms = mutableListOf<Film>()

    fun addToFavorite(film: Film){
        favoritesFilms.add(film)
    }

    fun delFromFavorites(film: Film){
        favoritesFilms.remove(film)
    }

    fun getFavoriteList(): List<Film>{
        return favoritesFilms
    }

}