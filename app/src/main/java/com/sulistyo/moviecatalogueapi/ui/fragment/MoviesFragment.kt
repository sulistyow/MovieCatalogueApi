package com.sulistyo.moviecatalogueapi.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.adapter.MoviesAdapter
import com.sulistyo.moviecatalogueapi.helper.BaseFragment
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.model.movie.kt.DataMovies
import com.sulistyo.moviecatalogueapi.visible
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie.*

class MoviesFragment : BaseFragment() {
    private lateinit var mAdapter: MoviesAdapter
    private var DATA = "data"
    var mData: ArrayList<DataMovies> = ArrayList()

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(DATA, mData)
        super.onSaveInstanceState(outState)
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

        mAdapter = MoviesAdapter(mData)

        rvMovie.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = mAdapter
        }

        if (savedInstanceState == null) {
            getMovie()
        } else {
            mData = savedInstanceState.getParcelableArrayList(DATA)!!
            mAdapter.updateData(mData)
            swipeRefresh.isRefreshing = false
        }

        swipeRefresh.setOnRefreshListener {
            getMovie()
        }
    }

    private fun getMovie() {
        cd?.add(
            ApiCall.instance().getMovie(ApiCall.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    swipeRefresh.isRefreshing = false
                    if (it.isSuccessful) {
                        rvMovie.visible()
                        if (it.body()?.results!!.isNotEmpty()) {
                            mData.clear()
                            mData.addAll(it.body()?.results!!)
                            mAdapter.updateData(mData)
                        }
                    } else Toast.makeText(activity, it.message(), Toast.LENGTH_LONG).show()
                }, {
                    swipeRefresh.isRefreshing = false
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
                ))
    }

    override fun onResume() {
        super.onResume()
        getMovie()
    }
}
