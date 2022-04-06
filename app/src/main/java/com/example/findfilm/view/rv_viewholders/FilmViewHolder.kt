package com.example.findfilm.view.rv_viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.findfilm.R
import com.example.findfilm.data.ApiConstants
import com.example.findfilm.domain.Film
import com.example.findfilm.view.customviews.RatingDonutView

class FilmViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.title)
    private val poster = itemView.findViewById<ImageView>(R.id.poster)
    private val description = itemView.findViewById<TextView>(R.id.description)
    //Находим наш прогресс бар для рейтинга
    private val ratingDonut = itemView.findViewById<RatingDonutView>(R.id.rating_donut)

    fun bind(film: Film){
        title.text = film.title
        //Указываем контейнер в котором будет "жить" наша картинка
        Glide.with(itemView)
        //Загружаем сам ресурс
            .load(ApiConstants.IMAGES_URL + "w342" + film.poster)
        //Центрируем изображение
            .centerCrop()
        //Указываем ImageView куда будем загружать изображение
            .into(poster)
//        poster.setImageResource(film.poster) <- старая реализация добавления картинки
        description.text = film.description
        //Устанавливаем рейтинг
        ratingDonut.setProgress((film.rating * 10).toInt())
    }

}