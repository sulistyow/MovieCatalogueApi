package com.sulistyo.moviecatalogueapi.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.model.tv.kt.DataTvShow
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvActivity : AppCompatActivity() {

    companion object {
        const val DATA = "movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        val tv: DataTvShow = intent.getParcelableExtra(DATA)
        initView(tv.posterPath!!, tv.name!!, tv.overview!!)

    }

    private fun initView(poster: String, title: String, overview: String) {
        Glide.with(this).load(ApiCall.img + poster).into(ivDetail)
        tvJudul.text = title
        tvDeskripsi.text = overview
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
