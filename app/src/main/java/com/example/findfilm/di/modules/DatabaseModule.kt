package com.example.findfilm.di.modules

import android.content.Context
import androidx.room.Room
import com.example.findfilm.data.AppDatabase
import com.example.findfilm.data.MainRepository
import com.example.findfilm.data.dao.FilmDao
import com.example.findfilm.data.db.DataBaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideFilmDao(context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "film_db"
    ).build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: FilmDao) = MainRepository(filmDao)
}