package com.example.findfilm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.example.findfilm.model.Film

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //ВНИМАНИЕ тут нужно всегда заменять на похожее надувание
        return inflater.inflate(R.layout.fragment_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //находим все нам нужные комплектующие
        val title = view.findViewById<Toolbar>(R.id.details_toolbar)
        val poster = view.findViewById<ImageView>(R.id.details_poster)
        val description = view.findViewById<TextView>(R.id.details_description)

        //получаем наш фильм через переданный бандл
        val film = arguments?.get("film") as Film

        //устанавливаем заголовок
        title.title = film.title

        //устанавливаем постер
        poster.setImageResource(film.poster)

        //устанавливаем описание
        description.text = film.description
    }



}