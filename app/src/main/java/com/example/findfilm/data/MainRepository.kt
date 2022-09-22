package com.example.findfilm.data

import android.content.ContentValues
import android.database.Cursor
import androidx.lifecycle.LiveData
import com.example.findfilm.data.dao.FilmDao
import com.example.findfilm.data.db.DataBaseHelper
import com.example.findfilm.data.entity.Film
import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы к бд должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDb(): LiveData<List<Film>> {
        return filmDao.getCachedFilms()
    }

}