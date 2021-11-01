package com.example.findfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_menu = findViewById<Button>(R.id.btn_menu).setOnClickListener{
            Toast.makeText(this, R.string.menu, Toast.LENGTH_SHORT).show()
        }
        val btn_catalog = findViewById<Button>(R.id.btn_catalog).setOnClickListener{
            Toast.makeText(this, R.string.catalog, Toast.LENGTH_SHORT).show()
        }
        val btn_watch_later = findViewById<Button>(R.id.btn_watch_later).setOnClickListener{
            Toast.makeText(this, R.string.watch_later, Toast.LENGTH_SHORT).show()
        }
        val btn_favorite = findViewById<Button>(R.id.btn_favorite).setOnClickListener{
            Toast.makeText(this, R.string.favorite, Toast.LENGTH_SHORT).show()
        }
        val btn_settings = findViewById<Button>(R.id.btn_settings).setOnClickListener{
            Toast.makeText(this, R.string.settings, Toast.LENGTH_SHORT).show()
        }



    }
}