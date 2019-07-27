package com.sulistyo.moviecatalogueapi.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.helper.Sp
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.model.movie.DataMovie
import com.sulistyo.moviecatalogueapi.model.tv.DataTv
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    lateinit var sp: Sp
    lateinit var movie: DataMovie
    lateinit var tv: DataTv

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        sp = Sp(this)

        if (sp.state().equals("movie")) {
            movie = Gson().fromJson(sp.movie(), DataMovie::class.java)
            initView(movie.posterPath.toString(), movie.title.toString(), movie.overview.toString())
        } else {
            tv = Gson().fromJson(sp.tv(), DataTv::class.java)
            initView(tv.posterPath.toString(), tv.name.toString(), tv.overview.toString())
        }

    }

    private fun initView(poster: String, title: String, overview: String) {

        Glide.with(this).load(ApiCall.img + poster).into(ivDetail)
        tvTitle.text = title
        tvOverview.text = overview
    }
}
