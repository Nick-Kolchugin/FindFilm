package com.example.findfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}