package com.example.findfilm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.findfilm.data.dao.FilmDao
import com.example.findfilm.data.entity.Film

@Database(entities = [Film :: class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}