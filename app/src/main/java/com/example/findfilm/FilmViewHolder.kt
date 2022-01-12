package com.example.findfilm

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.findfilm.model.Film

class FilmViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.title)
    private val poster = itemView.findViewById<ImageView>(R.id.poster)
    private val description = itemView.findViewById<TextView>(R.id.description)

    fun bind(film: Film){
        title.text = film.title
        //Указываем контейнер в котором будет "жить" наша картинка
        Glide.with(itemView)
        //Загружаем сам ресурс
            .load(film.poster)
        //Центрируем изображение
            .centerCrop()
        //Указываем ImageView куда будем загружать изображение
            .into(poster)
//        poster.setImageResource(film.poster) <- старая реализация добавления картинки
        description.text = film.description
    }

}