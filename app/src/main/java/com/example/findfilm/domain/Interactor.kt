package com.example.findfilm.domain

import com.example.findfilm.data.API
import com.example.findfilm.data.MainRepository
import com.example.findfilm.data.TmdbApi
import com.example.findfilm.data.entity.TmdbResultsDto
import com.example.findfilm.utils.Converter
import com.example.findfilm.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi) {

    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback){
        retrofitService.getFilms(API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>){
                //При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список фильмов
                callback.onSuccess(Converter.convertApiListToDtoList(response.body()?.results))
                //println("!!! ${response.body()}")
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                //в случае провала вызываем дургой метод
                callback.onFailure()
            }
        })
    }

}