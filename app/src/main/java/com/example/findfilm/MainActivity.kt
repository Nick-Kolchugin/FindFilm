package com.example.findfilm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.findfilm.model.Film
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()

        val topAppBar = findViewById<MaterialToolbar>(R.id.top_app_bar)
        val navToolBar = findViewById<BottomNavigationView>(R.id.bottom_nav_bar)



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

    fun launchDetailsFragment(film: Film){
        //создаем посылку
        val bundle = Bundle()
        //кладем наш фильм в посылку
        bundle.putParcelable("film", film)
        //кладем фрагмент с деталями в переменную
        val fragment = DetailsFragment()
        //прикрепляем нашу посылку к фрагменту
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }
}