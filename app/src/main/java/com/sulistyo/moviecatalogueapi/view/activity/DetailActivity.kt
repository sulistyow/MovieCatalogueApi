package com.sulistyo.moviecatalogueapi.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.helper.Helper
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.model.movie.DataMovie
import com.sulistyo.moviecatalogueapi.model.tv.DataTv
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {


    lateinit var movie: DataMovie
    lateinit var tv: DataTv

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val mData: DataMovie = intent.getParcelableExtra(Helper.DATA)
        Log.d("datane :", mData.toString())

        val st = intent.getStringExtra(Helper.STATE)
        Log.d("state :", st)

        if (st.equals("movie")) {

        }


    }

    private fun initView(poster: String, title: String, overview: String) {


        Glide.with(this).load(ApiCall.img + poster).into(ivDetail)
        tvTitle.text = title
        tvOverview.text = overview
    }
}
