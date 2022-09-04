package com.example.findfilm.di

import com.example.findfilm.di.modules.DatabaseModule
import com.example.findfilm.di.modules.DomainModule
import com.example.findfilm.di.modules.RemoteModule
import com.example.findfilm.viewmodel.HomeFragmentViewModel
import com.example.findfilm.viewmodel.SettingsFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        //Внедряем все модули, нужные для этого компонента
        DatabaseModule::class,
        DomainModule::class,
        RemoteModule::class
    ]
)
interface AppComponent {
    //Метод для внедрения зависимостей в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)

    //Метод для внедрения зависимостей в SettingsFragmentViewModel
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}