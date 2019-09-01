package com.sulistyo.moviecatalogueapi.ui.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.adapter.MoviesAdapter
import com.sulistyo.moviecatalogueapi.adapter.TvAdapter
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.model.movie.kt.DataMovies
import com.sulistyo.moviecatalogueapi.model.tv.kt.DataTvShow
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    private lateinit var mAdapter: MoviesAdapter
    private lateinit var tAdapter: TvAdapter
    private var DATA = "data"
    var mData: ArrayList<DataMovies> = ArrayList()
    var tData: ArrayList<DataTvShow> = ArrayList()
    var cd = CompositeDisposable()

    companion object {
        const val SEARCH = "from"
    }

    override fun onDestroy() {
        super.onDestroy()
        cd.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }
        back.setOnClickListener { finish() }

        val anu = intent.getStringExtra(SEARCH)
        Log.d("search dari ", anu)
        mAdapter = MoviesAdapter(mData)
        tAdapter = TvAdapter(tData)

        searchView.isIconified = false

        searchData(searchView)
    }

    private fun searchData(searchView: SearchView) {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                when (intent.getStringExtra(SEARCH)) {
                    "Movie" -> {
                        searchMovie(query)
                        rvSearch.apply {
                            layoutManager = GridLayoutManager(this@SearchActivity, 2)
                            adapter = mAdapter
                        }
                    }
                    "Tv" -> {
                        searchTV(query)
                        rvSearch.apply {
                            layoutManager = GridLayoutManager(this@SearchActivity, 2)
                            adapter = tAdapter
                        }
                    }
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }

        })
    }

    private fun searchMovie(query: String) {
        cd.add(
            ApiCall.instance().searchMovie(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) {
                        if (it.body()?.results!!.isNotEmpty()) {
                            mData.clear()
                            mData.addAll(it.body()?.results!!)
                            mAdapter.updateData(mData)
                        } else Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                    }
                }, {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                })
        )
    }

    private fun searchTV(query: String) {
        cd.add(
            ApiCall.instance().searchTv(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) {
                        if (it.body()?.results!!.isNotEmpty()) {
                            tData.clear()
                            tData.addAll(it.body()?.results!!)
                            tAdapter.updateData(tData)
                        } else Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                    }
                }, {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                })
        )
    }

}
