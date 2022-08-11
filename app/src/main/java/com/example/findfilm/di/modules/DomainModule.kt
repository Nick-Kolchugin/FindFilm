package com.example.findfilm.di.modules

import com.example.findfilm.data.MainRepository
import com.example.findfilm.data.TmdbApi
import com.example.findfilm.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) = Interactor(
        repo = repository,
        retrofitService = tmdbApi
    )

}