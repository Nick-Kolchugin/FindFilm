package com.example.findfilm

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findfilm.model.Film
import com.example.findfilm.model.FilmListRecyclerAdapter
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listOfFilms = listOf(
            Film(getString(R.string.shang_chi_title), R.drawable.shang_chi_poster, getString(R.string.shang_chi_desc)),
            Film(getString(R.string.freeguy_title), R.drawable.freeguy_poster, getString(R.string.freeguy_desc)),
            Film(getString(R.string.dune_part1_title), R.drawable.dune_poster, getString(R.string.dune_part1_desc)),
            Film(getString(R.string.house_of_gucci_title), R.drawable.house_of_gucci_poster, getString(R.string.house_of_gucci_desc)),
            Film(getString(R.string.venom2_title), R.drawable.venom2_poster, getString(R.string.venom2_desc)),
            Film(getString(R.string.ghostbusters_afterlife_title), R.drawable.ghostbusters_afterlife_poster, getString(R.string.ghostbusters_afterlife_desc)),
            Film(getString(R.string.last_night_in_soho_title), R.drawable.last_night_in_soho_poster, getString(R.string.last_night_in_soho_desc)),
            Film(getString(R.string.no_time_to_die_title), R.drawable.no_time_to_die_poster, getString(R.string.no_time_to_die_desc)),
            Film(getString(R.string.cruella_title), R.drawable.cruella_poster, getString(R.string.cruella_desc)),
            Film(getString(R.string.mortal_kombat_title), R.drawable.mortal_kombat_poster, getString(R.string.mortal_kombat_desc)),
        )

        val searchFilmView = view.findViewById<SearchView>(R.id.search_film_view)
        searchFilmView.setOnClickListener{
            searchFilmView.isIconified = false
        }
        //подключаем слушателя изменений введеного текста
        searchFilmView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            //этот метод отрабатывает кнопку поиск на клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }
            //этот метод обрабатывает на каждое изменение текста
            override fun onQueryTextChange(newText: String?): Boolean {
                //если ввод пустой то вставляем в адаптер всю бд
                if(newText?.isEmpty()== true){
                    filmsAdapter.addItems(listOfFilms)
                    return true
                }
                //фильтруем список на поиск подходящих сочетаний
                val result = listOfFilms.filter {
                    //чтобы все работало правильно нужно и запрос и имя приводить к нижнему регистру
                    it.title.lowercase(Locale.getDefault()).contains(newText?.lowercase(Locale.getDefault())?:"")//не уверен что так правлильно с элвисом но что поделать
                }
                filmsAdapter.addItems(result)
                return true
            }

        })

        val mainRecycler = view.findViewById<RecyclerView>(R.id.main_recycler_view)

        mainRecycler.apply {

            filmsAdapter = FilmListRecyclerAdapter(object: FilmListRecyclerAdapter.OnClickListener{
                override fun click(film: Film) {
                    (requireContext() as MainActivity).launchDetailsFragment(film)
                }
            })
            adapter = filmsAdapter

            layoutManager = LinearLayoutManager(requireContext())

            addItemDecoration(TopSpacingItemDecoration(8))

            filmsAdapter.addItems(listOfFilms)
        }
    }


}