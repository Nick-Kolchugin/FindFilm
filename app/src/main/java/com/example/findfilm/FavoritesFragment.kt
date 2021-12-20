package com.example.findfilm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findfilm.model.Film
import com.example.findfilm.model.FilmListRecyclerAdapter

class FavoritesFragment : Fragment() {

    lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoritesRecycler = view.findViewById<RecyclerView>(R.id.favorites_recycler_view)
        val favoritesList = (requireActivity() as MainActivity).favoritesDB.getFavoriteList()

        favoritesRecycler.apply {
            filmsAdapter = FilmListRecyclerAdapter(object: FilmListRecyclerAdapter.OnClickListener{
                override fun click(film: Film) {
                    (requireActivity() as MainActivity).launchDetailsFragment(film)
                }
            })

            //присваиваем адаптер
            adapter = filmsAdapter

            layoutManager = LinearLayoutManager(requireContext())

            addItemDecoration(TopSpacingItemDecoration(8))

            filmsAdapter.addItems(favoritesList)
        }


    }
}