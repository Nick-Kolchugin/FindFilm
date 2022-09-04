package com.example.findfilm.di.modules

import android.content.Context
import com.example.findfilm.data.MainRepository
import com.example.findfilm.data.db.DataBaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDataBaseHelper(context: Context) = DataBaseHelper(context)

    @Provides
    @Singleton
    fun provideRepository(dataBaseHelper: DataBaseHelper) = MainRepository(dataBaseHelper)
}