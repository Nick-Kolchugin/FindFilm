package com.example.findfilm.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findfilm.view.rv_adapters.FilmListRecyclerAdapter
import com.example.findfilm.view.activities.MainActivity
import com.example.findfilm.view.rv_adapters.TopSpacingItemDecoration
import com.example.findfilm.databinding.FragmentFavoritesBinding
import com.example.findfilm.data.entity.Film

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
        MainActivity.AnimationHelper.performFragmentCircularRevealAnimation(
            binding.favoritesFragmentRoot,
            requireActivity(),
            1
        )

        //Благодря использованию ViewDataBinding нажняя строчка больше не используется
        //val favoritesRecycler = view.findViewById<RecyclerView>(R.id.favorites_recycler_view)
        val favoritesList = (requireActivity() as MainActivity).favoritesDB.getFavoriteList()

        binding.favoritesRecyclerView.apply {
            filmsAdapter = FilmListRecyclerAdapter(object: FilmListRecyclerAdapter.OnClickListener {
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