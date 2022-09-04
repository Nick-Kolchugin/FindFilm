package com.example.findfilm

import android.app.Application
import com.example.findfilm.di.AppComponent
import com.example.findfilm.di.DaggerAppComponent
import com.example.findfilm.di.modules.DatabaseModule
import com.example.findfilm.di.modules.DomainModule
import com.example.findfilm.di.modules.RemoteModule

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.builder().apply {
            remoteModule(RemoteModule())
            databaseModule(DatabaseModule())
            domainModule(DomainModule(this@App))
        }.build()

    }

    companion object {
        lateinit var instance: App
            private set
    }
}