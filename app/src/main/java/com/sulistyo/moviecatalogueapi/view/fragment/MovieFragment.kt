package com.sulistyo.moviecatalogueapi.view.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.adapter.MovieAdapter
import com.sulistyo.moviecatalogueapi.helper.BaseFragment
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.invisible
import com.sulistyo.moviecatalogueapi.model.movie.DataMovie
import com.sulistyo.moviecatalogueapi.view.activity.DetailActivity
import com.sulistyo.moviecatalogueapi.visible
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.layout_kosong.*

class MovieFragment : BaseFragment() {
    private lateinit var mAdapter: MovieAdapter

    companion object {
        fun newInstance(): MovieFragment {
            return MovieFragment()
        }
    }

    fun showEmptyData() {
        swipeRefresh.isRefreshing = false
        rvMovie.invisible()
        kosong.visible()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh.isRefreshing = true
        getMovie()
        swipeRefresh.setOnRefreshListener {
            getMovie()
        }
    }

    fun getMovie() {
        cd?.add(
            ApiCall.instance().getMovie(ApiCall.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    swipeRefresh.isRefreshing = false
                    if (it.isSuccessful) {
                        rvMovie.visible()
                        kosong.invisible()
                        if (it.body()?.results!!.isNotEmpty()) {
                            mAdapter = MovieAdapter(it.body()?.results!!) {
                                data.movie(
                                    Gson().toJson(
                                        DataMovie(
                                            overview = it.overview,
                                            title = it.title,
                                            posterPath = it.posterPath
                                        )
                                    )
                                )
                                data.state("movie")
                                startActivity(Intent(activity, DetailActivity::class.java))
                            }
                            rvMovie.apply {
                                layoutManager = GridLayoutManager(activity, 2)
                                adapter = mAdapter
                            }
                        }
                    } else showEmptyData()
                }, {
                    swipeRefresh.isRefreshing = false
                    showEmptyData()
                }
                ))
    }

}
