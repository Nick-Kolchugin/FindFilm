package com.example.findfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findfilm.App
import com.example.findfilm.data.entity.Film
import com.example.findfilm.domain.Interactor
import org.koin.core.KoinComponent
import java.util.concurrent.Executors
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel(), KoinComponent {
    val filmsListLiveData : LiveData<List<Film>>
    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        filmsListLiveData = interactor.getFilmsFromDB()
        getFilms()
    }

    fun getFilms(){
        showProgressBar.postValue(true)
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess() {
                showProgressBar.postValue(false)
            }

            override fun onFailure() {
                Executors.newSingleThreadExecutor().execute {
                    showProgressBar.postValue(false)
                }
            }
        })
    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
    }

}

