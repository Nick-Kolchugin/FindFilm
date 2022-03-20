package com.example.findfilm.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findfilm.App
import com.example.findfilm.domain.Film
import com.example.findfilm.domain.Interactor

class HomeFragmentViewModel: ViewModel() {
    val filmsListLiveData = MutableLiveData<List<Film>>()
    private var interactor: Interactor = App.instance.interactor
    init{
        val films = interactor.getFilmsDB()
        filmsListLiveData.postValue(films)
    }
}