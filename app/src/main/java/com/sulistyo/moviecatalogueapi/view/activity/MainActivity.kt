package com.sulistyo.moviecatalogueapi.view.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.view.fragment.MovieFragment
import com.sulistyo.moviecatalogueapi.view.fragment.TvShowFragment


class MainActivity : AppCompatActivity() {

    var TAG = "tag"
    var mFragment: Fragment = Fragment()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_movie -> {
                changeFragment(MovieFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_tv -> {
                changeFragment(TvShowFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("tag", TAG)
        supportFragmentManager.putFragment(outState, TAG, mFragment)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        /* TAG = savedInstanceState.getString("tag")!!
         mFragment = supportFragmentManager.getFragment(
             savedInstanceState,
             TAG
         )!!*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        if(null==savedInstanceState){
            changeFragment(MovieFragment.newInstance())
        }
    }

    private fun changeFragment(fragment: Fragment){
        val transact=supportFragmentManager.beginTransaction()
        transact.replace(R.id.frameMain,fragment)
        transact.commit()
    }

   /* private fun selectedFragment(fragment: Fragment, tag: String): Boolean {
        mFragment = fragment
        TAG = tag
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameMain, fragment, tag)
            .commit()
        return true
    }*/

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
