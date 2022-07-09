package com.example.findfilm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findfilm.domain.Film
import com.example.findfilm.domain.Interactor
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.android.ext.android.inject

class HomeFragmentViewModel : ViewModel(), KoinComponent {
    val filmsListLiveData : MutableLiveData<List<Film>> = MutableLiveData()
    private val interactor: Interactor by inject()

    init {
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess(films: List<Film>) {
                filmsListLiveData.postValue(films)
                //println("!!! $films")
            }

            override fun onFailure() {
                println("!!! failure")
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(films: List<Film>)
        fun onFailure()
    }

}

