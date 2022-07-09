package com.example.findfilm

import android.app.Application
import android.content.Context
import com.example.findfilm.data.ApiConstants
import com.example.findfilm.data.MainRepository
import com.example.findfilm.data.TmdbApi
import com.example.findfilm.di.DI
import com.example.findfilm.domain.Interactor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            //Прикрепляем контекст
            androidContext(this@App)
            //(Опционально) подключаем зависимость
            androidLogger()
            //Инициализируем модули
            modules(listOf(DI.mainModule))
        }

    }



}