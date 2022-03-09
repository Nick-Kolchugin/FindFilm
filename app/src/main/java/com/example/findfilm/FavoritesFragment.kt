package com.example.findfilm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findfilm.databinding.FragmentFavoritesBinding
import com.example.findfilm.model.Film
import com.example.findfilm.model.FilmListRecyclerAdapter

class FavoritesFragment : Fragment() {

    lateinit var filmsAdapter: FilmListRecyclerAdapter
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Благодря использованию ViewDataBinding нажняя строчка больше не используется
        //val favoritesFragmentRoot = view.findViewById<FrameLayout>(R.id.favorites_fragment_root)
        MainActivity.AnimationHelper.performFragmentCircularRevealAnimation(binding.favoritesFragmentRoot, requireActivity(), 1)

        //Благодря использованию ViewDataBinding нажняя строчка больше не используется
        //val favoritesRecycler = view.findViewById<RecyclerView>(R.id.favorites_recycler_view)
        val favoritesList = (requireActivity() as MainActivity).favoritesDB.getFavoriteList()

        binding.favoritesRecyclerView.apply {
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