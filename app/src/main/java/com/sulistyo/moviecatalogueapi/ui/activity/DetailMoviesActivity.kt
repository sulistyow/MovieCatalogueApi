package com.sulistyo.moviecatalogueapi.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.data.MovieFavorite
import com.sulistyo.moviecatalogueapi.helper.BaseActivity
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.model.movie.kt.DataMovies
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.jetbrains.anko.toast

class DetailMoviesActivity : BaseActivity() {

    companion object {
        const val DATA = "movie"
        const val STATE = "state"
    }

    private var menuItem: Menu? = null
    private var isFavorite = false
    var movie: DataMovies? = null
    var movieFav: MovieFavorite? = null
    private var title = "title"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        progress.visibility = View.GONE

        when (intent.getStringExtra(STATE)) {

            "fav" -> {
                movieFav = intent.getParcelableExtra(DATA)
                initView(movieFav?.posterPath!!, movieFav!!.title!!, movieFav!!.overview!!)
                checkFavorite(movieFav?.title!!)
                title = movieFav?.title!!
            }
            "movie" -> {
                movie = intent.getParcelableExtra(DATA)
                initView(movie?.posterPath!!, movie!!.title!!, movie!!.overview!!)
                checkFavorite(movie?.title!!)
                title = movie?.title!!
            }
        }
    }

    private fun checkFavorite(title: String) {
        cd?.add(
            Observable.fromCallable { favoriteDb?.dataDao()!!.checkMovie(title) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.isNotEmpty()) {
                        isFavorite = true
                    }
                }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                isFavorite = if (!isFavorite) {
                    insertData(
                        MovieFavorite(
                            title = movie?.title.toString(),
                            overview = movie?.overview.toString(),
                            posterPath = movie?.posterPath.toString()
                        )
                    )
                    true
                } else {
                    deleteMovieFav(
                        title
                    )
                    false
                }
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun insertData(data: MovieFavorite) {
        cd?.add(
            Observable.fromCallable { favoriteDb?.dataDao()!!.insertMovie(data) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    toast("Saved to favorites")
                    isFavorite = true
                }
        )
    }

    private fun initView(poster: String, title: String, overview: String) {
        Glide.with(this).load(ApiCall.img + poster).into(ivDetail)
        tvJudul.text = title
        tvDeskripsi.text = overview
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        } else menuItem?.getItem(0)?.icon =
            ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
