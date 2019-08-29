package com.sulistyo.moviecatalogueapi.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.data.model.TvFavorite
import com.sulistyo.moviecatalogueapi.helper.BaseActivity
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.model.tv.kt.DataTvShow
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import org.jetbrains.anko.toast

class DetailTvActivity : BaseActivity() {

    companion object {
        const val DATA = "movie"
        const val STATE = "state"
    }

    private var menuItem: Menu? = null
    private var isFavorite = false
    var tv: DataTvShow? = null
    var tvFav: TvFavorite? = null
    private var title = "title"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        progress.visibility = View.GONE
        when (intent.getStringExtra(STATE)) {
            "fav" -> {
                tvFav = intent.getParcelableExtra(DATA)
                initView(tvFav?.posterPath!!, tvFav?.name!!, tvFav?.overview!!)
                checkFavorite(tvFav?.name!!)
                title = tvFav?.name!!
            }
            "tv" -> {
                tv = intent.getParcelableExtra(DATA)
                initView(tv?.posterPath!!, tv?.name!!, tv?.overview!!)
                checkFavorite(tv?.name!!)
                title = tv?.name!!
            }
        }

    }

    private fun checkFavorite(name: String) {
        cd?.add(
            Observable.fromCallable { favoriteDb?.dataDao()!!.checkTv(name) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.isNotEmpty()) {
                        isFavorite = true
                    }
                }
        )
    }

    private fun initView(poster: String, title: String, overview: String) {
        Glide.with(this).load(ApiCall.img + poster).into(ivDetail)
        tvJudul.text = title
        tvDeskripsi.text = overview
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
                        TvFavorite(
                            name = tv?.name.toString(),
                            overview = tv?.overview.toString(),
                            posterPath = tv?.posterPath.toString()
                        )
                    )
                    true
                } else {
                    deleteTvFav(
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

    private fun insertData(data: TvFavorite) {
        cd?.add(
            Observable.fromCallable { favoriteDb?.dataDao()!!.insertTv(data) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    toast("Saved to favorites")
                    isFavorite = true
                }
        )
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
