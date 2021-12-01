package com.example.findfilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findfilm.model.Film
import com.example.findfilm.model.FilmListRecyclerAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topAppBar = findViewById<MaterialToolbar>(R.id.top_app_bar)
        val navToolBar = findViewById<BottomNavigationView>(R.id.bottom_nav_bar)
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
        val mainRecycler = findViewById<RecyclerView>(R.id.main_recycler_view)

        mainRecycler.apply {

            filmsAdapter = FilmListRecyclerAdapter(object: FilmListRecyclerAdapter.OnClickListener{
                override fun click(film: Film) {
                    //Создаем бандл и кладем туда объект с данными фильма
                    val bundle = Bundle()
                    //Первым параметром указывается ключ, по которому потом будем искать, вторым сам
                    //передаваемый объект
                    bundle.putParcelable("film", film)
                    //Запускаем наше активити
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    //Прикрепляем бандл к интенту
                    intent.putExtras(bundle)
                    //Запускаем наше активити через интент
                    startActivity(intent)
                }
            })

            adapter = filmsAdapter

            layoutManager = LinearLayoutManager(this@MainActivity)

            addItemDecoration(TopSpacingItemDecoration(8))

            filmsAdapter.addItems(listOfFilms)
        }


        topAppBar.setNavigationOnClickListener {
            Toast.makeText(this, R.string.menu, Toast.LENGTH_SHORT).show()
        }
        topAppBar.setOnMenuItemClickListener {
            when (it.itemId){
                R.id.top_bar_settings -> {
                    Toast.makeText(this, R.string.settings, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        navToolBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_favorite -> {
                    Toast.makeText(this, R.string.favorite, Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_catalog -> {
                    Toast.makeText(this, R.string.catalog, Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_watch_later -> {
                    Toast.makeText(this, R.string.watch_later, Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }

    }
}