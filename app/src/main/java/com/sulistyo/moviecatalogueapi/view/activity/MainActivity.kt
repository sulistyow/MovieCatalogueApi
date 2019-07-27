package com.sulistyo.moviecatalogueapi.view.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.view.fragment.MovieFragment
import com.sulistyo.moviecatalogueapi.view.fragment.TvShowFragment


class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_movie -> {
                selectedFragment(MovieFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_tv -> {
                selectedFragment(TvShowFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
            selectedFragment(MovieFragment())
    }

    private fun selectedFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameMain, fragment)
            .commit()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
