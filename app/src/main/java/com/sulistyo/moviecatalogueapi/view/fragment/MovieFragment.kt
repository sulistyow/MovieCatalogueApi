package com.sulistyo.moviecatalogueapi.view.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.adapter.MovieAdapter
import com.sulistyo.moviecatalogueapi.helper.BaseFragment
import com.sulistyo.moviecatalogueapi.helper.Helper
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
    private val STATE = "state"
    private val DATA = "data"
    private val mode: Int = 0

    companion object{
        fun newInstance():MovieFragment{
            return MovieFragment()
        }
    }

    var mData: ArrayList<DataMovie> = ArrayList()

    fun showEmptyData() {
        swipeRefresh.isRefreshing = false
        rvMovie.invisible()
        kosong.visible()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(DATA, mData)
        super.onSaveInstanceState(outState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (savedInstanceState != null) {
            mData = savedInstanceState.getParcelableArrayList(DATA)!!

        } else {
            getMovie()
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         mAdapter = MovieAdapter(mData) {
             Log.d("DataMovie :", mData.toString())
             val i = Intent(activity, DetailActivity::class.java)
             i.putExtra(Helper.STATE, "movie")
             i.putExtra(Helper.DATA, mData)
             startActivity(i)
         }
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
                            mData.clear()
                            mData.addAll(it.body()?.results!!)
                            mAdapter.updateData(mData)
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

    override fun onResume() {
        super.onResume()
        /* mAdapter = MovieAdapter(mData) {
             startActivity(Intent(activity, DetailActivity::class.java))
         }*/
    }
}
