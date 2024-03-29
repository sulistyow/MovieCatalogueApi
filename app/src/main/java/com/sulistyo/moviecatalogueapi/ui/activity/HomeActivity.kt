package com.sulistyo.moviecatalogueapi.ui.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.reminder.ReminderActivity
import com.sulistyo.moviecatalogueapi.ui.fragment.FavoriteFragment
import com.sulistyo.moviecatalogueapi.ui.fragment.MoviesFragment
import com.sulistyo.moviecatalogueapi.ui.fragment.TvFragment
import org.jetbrains.anko.startActivity


class HomeActivity : AppCompatActivity() {
    val KEY_FRAGMENT = "fragment"
    var TAG = "tag"
    var mFragment: Fragment = MoviesFragment()

    var SearchFor: String = "search"

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_movie -> {
                    SearchFor = "Movie"
                    changeFragment(MoviesFragment(), "Movie")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_tv -> {
                    SearchFor = "Tv"
                    changeFragment(TvFragment(), "Tv")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_fav -> {
                    changeFragment(FavoriteFragment(), "FavoriteDb")
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        if (savedInstanceState == null) {
            SearchFor = "Movie"
            changeFragment(mFragment, TAG)
        } else {
            SearchFor = KEY_FRAGMENT
            TAG = savedInstanceState.getString(KEY_FRAGMENT)!!
            mFragment = supportFragmentManager.getFragment(savedInstanceState, TAG)!!
            changeFragment(mFragment, TAG)
        }
    }

    private fun changeFragment(fragment: Fragment, tag: String) {
        mFragment = fragment
        TAG = tag
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameMain, fragment, TAG)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_FRAGMENT, TAG)
        supportFragmentManager.putFragment(outState, TAG, mFragment)
        super.onSaveInstanceState(outState)
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
            R.id.search -> {
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra(SearchActivity.SEARCH, SearchFor)
                startActivity(intent)
            }
            R.id.reminder -> {
                startActivity<ReminderActivity>()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
