package com.example.findfilm.domain

import com.example.findfilm.data.MainRepository

class Interactor(val repo: MainRepository) {

    fun getFilmsDB(): List<Film> = repo.filmsDataBase

}