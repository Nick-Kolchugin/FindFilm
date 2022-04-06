package com.example.findfilm.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findfilm.view.rv_adapters.FilmListRecyclerAdapter
import com.example.findfilm.view.activities.MainActivity
import com.example.findfilm.R
import com.example.findfilm.view.rv_adapters.TopSpacingItemDecoration
import com.example.findfilm.databinding.FragmentHomeBinding
import com.example.findfilm.domain.Film
import com.example.findfilm.viewmodel.HomeFragmentViewModel
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }
    private var filmsDataBase = listOf<Film>()
        //Инициализируем backing field
        set(value) {
            //Если приходит такое же значение то мы выходим из метода
            if (field == value) return
            //Если пришло другое значение то кладем его в переменную
            field = value
            //Обновляем RV адаптер
            filmsAdapter.addItems(field)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Благодря использованию ViewDataBinding нажняя строчка больше не используется
        //val homeFragmentRoot = view.findViewById<ConstraintLayout>(R.id.home_fragment_root)
        MainActivity.AnimationHelper.performFragmentCircularRevealAnimation(
            binding.homeFragmentRoot,
            requireActivity(),
            2
        )

        //подписываемся на изменения ViewModel
        viewModel.filmsListLiveData.observe(viewLifecycleOwner, Observer<List<Film>> {
            filmsDataBase = it
        })

        /*val listOfFilms = listOf(
            Film(getString(R.string.shang_chi_title),
                R.drawable.shang_chi_poster, getString(R.string.shang_chi_desc), 7.0f),
            Film(getString(R.string.freeguy_title),
                R.drawable.freeguy_poster, getString(R.string.freeguy_desc), 7.4f),
            Film(getString(R.string.dune_part1_title),
                R.drawable.dune_poster, getString(R.string.dune_part1_desc), 7.7f),
            Film(getString(R.string.house_of_gucci_title),
                R.drawable.house_of_gucci_poster, getString(R.string.house_of_gucci_desc), 7.2f),
            Film(getString(R.string.venom2_title),
                R.drawable.venom2_poster, getString(R.string.venom2_desc), 6.1f),
            Film(getString(R.string.ghostbusters_afterlife_title),
                R.drawable.ghostbusters_afterlife_poster, getString(R.string.ghostbusters_afterlife_desc), 7.0f),
            Film(getString(R.string.last_night_in_soho_title),
                R.drawable.last_night_in_soho_poster, getString(R.string.last_night_in_soho_desc), 6.7f),
            Film(getString(R.string.no_time_to_die_title),
                R.drawable.no_time_to_die_poster, getString(R.string.no_time_to_die_desc), 7.1f),
            Film(getString(R.string.cruella_title),
                R.drawable.cruella_poster, getString(R.string.cruella_desc), 7.4f),
            Film(getString(R.string.mortal_kombat_title),
                R.drawable.mortal_kombat_poster, getString(R.string.mortal_kombat_desc), 6.2f),
        )*/

        //Благодря использованию ViewDataBinding нажняя строчка больше не используется
        //val searchFilmView = view.findViewById<SearchView>(R.id.search_film_view)
        binding.searchFilmView.setOnClickListener()
        {
            binding.searchFilmView.isIconified = false
        }
        //подключаем слушателя изменений введеного текста
        binding.searchFilmView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                //этот метод отрабатывает кнопку поиск на клавиатуре
                override fun onQueryTextSubmit(query: String?): Boolean {
                    TODO("Not yet implemented")
                }

                //этот метод обрабатывает на каждое изменение текста
                override fun onQueryTextChange(newText: String?): Boolean {
                    //если ввод пустой то вставляем в адаптер всю бд
                    if (newText?.isEmpty() == true) {
                        filmsAdapter.addItems(filmsDataBase)
                        return true
                    }
                    //фильтруем список на поиск подходящих сочетаний
                    val result = filmsDataBase.filter {
                        //чтобы все работало правильно нужно и запрос и имя приводить к нижнему регистру
                        it.title.lowercase(Locale.getDefault()).contains(
                            newText?.lowercase(Locale.getDefault()) ?: ""
                        )//не уверен что так правлильно с элвисом но что поделать
                    }
                    filmsAdapter.addItems(result)
                    return true
                }

            })

        //Благодря использованию ViewDataBinding нажняя строчка больше не используется
        //val mainRecycler = view.findViewById<RecyclerView>(R.id.main_recycler_view)

        binding.mainRecyclerView.apply()
        {

            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnClickListener {
                    override fun click(film: Film) {
                        (requireContext() as MainActivity).launchDetailsFragment(film)
                    }
                })
            adapter = filmsAdapter

            layoutManager = LinearLayoutManager(requireContext())

            addItemDecoration(TopSpacingItemDecoration(8))

            filmsAdapter.addItems(filmsDataBase)
        }
    }


}