package com.example.findfilm

import android.app.Application
import android.content.Context
import com.example.findfilm.data.MainRepository
import com.example.findfilm.domain.Interactor

class App: Application() {
    lateinit var repo: MainRepository
    lateinit var interactor: Interactor

    override fun onCreate() {
        super.onCreate()

        //Инициализиурем экземпляр App, через который будем получать доступ к остальным переменным
        instance = this
        //Инициализируем репозиторий
        repo = MainRepository()
        //Инициализируем интерактор
        interactor = Interactor(repo)
    }

    companion object{
        //Здесь статически хранится ссылка на экземпляр App
        lateinit var instance: App
        //Приватный сеттер, чтоб нельзя было в эту переменную присвоить что либо другое
        private set
    }

}