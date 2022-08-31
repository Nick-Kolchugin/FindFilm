package com.example.findfilm.di.modules

import android.content.Context
import com.example.findfilm.data.MainRepository
import com.example.findfilm.data.PreferenceProvider
import com.example.findfilm.data.TmdbApi
import com.example.findfilm.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
//Передаем контекст для SharedPreferences через конструктор
class DomainModule(val context: Context) {

    //Нам нужно этот контекст както провайдить, поэтому создаем провайдер
    @Provides
    fun provideContext() = context

    //Создаем экземпляр SharedPreferences
    @Provides
    @Singleton
    fun providePreferences(context: Context) = PreferenceProvider(context)

    @Provides
    @Singleton
    fun provideInteractor(
        repository: MainRepository,
        tmdbApi: TmdbApi,
        preferenceProvider: PreferenceProvider
    ) = Interactor(
        repo = repository,
        retrofitService = tmdbApi,
        preferences = preferenceProvider,
    )

}