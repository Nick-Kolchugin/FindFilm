package com.example.findfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.findfilm.model.Film
import com.google.android.material.snackbar.Snackbar

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //находим все нам нужные комплектующие
        val title = findViewById<Toolbar>(R.id.details_toolbar)
        val poster = findViewById<ImageView>(R.id.details_poster)
        val description =  findViewById<TextView>(R.id.details_description)
        val container =  findViewById<CoordinatorLayout>(R.id.details_container)

        //получаем наш фильм через переданный бандл
        val film = intent.extras?.get("film") as Film

        //устанавливаем заголовок
        title.title = film.title

        //устанавливаем постер
        poster.setImageResource(film.poster)

        //устанавливаем описание
        description.text = film.description


    }
}