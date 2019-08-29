package com.sulistyo.moviecatalogueapi.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.GridLayoutManager
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.adapter.MovieFavAdapter
import com.sulistyo.moviecatalogueapi.adapter.TvFavAdapter
import com.sulistyo.moviecatalogueapi.data.MovieFavorite
import com.sulistyo.moviecatalogueapi.data.model.TvFavorite
import com.sulistyo.moviecatalogueapi.helper.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment() {

    private val listMovie: MutableList<MovieFavorite> = mutableListOf()
    private val listTv: MutableList<TvFavorite> = mutableListOf()

    private var mAdapter: MovieFavAdapter? = null
    private val tAdapter = TvFavAdapter(listTv)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = MovieFavAdapter(listMovie)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                setupRecycler()
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                setupRecycler()
            }
        }

        swipeRefresh.setOnRefreshListener {
            setupRecycler()
            swipeRefresh.isRefreshing = false
        }

    }

    private fun setupRecycler() {
        when (spinner.selectedItemPosition) {
            0 -> {
                getMovie()
                rvFavorite.apply {
                    adapter = mAdapter
                    layoutManager = GridLayoutManager(this@FavoriteFragment.context, 2)
                }
                getMovie()
            }
            1 -> {
                rvFavorite.apply {
                    adapter = tAdapter
                    layoutManager = GridLayoutManager(this@FavoriteFragment.context, 2)
                }
                getTv()
            }
        }
    }

    private fun getTv() {
        cd?.add(
            favoriteDb.dataDao().getTvFav()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.isNotEmpty()) {
                        tvEmpty.visibility = View.GONE
                        listTv.clear()
                        listTv.addAll(it)
                        tAdapter.notifyDataSetChanged()
                    }
                }
        )

    }

    private fun getMovie() {
        cd?.add(
            favoriteDb.dataDao().getMovieFav()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.isNotEmpty()) {
                        tvEmpty.visibility = View.GONE
                        listMovie.clear()
                        listMovie.addAll(it)
                        mAdapter?.notifyDataSetChanged()
                    }
                }
        )
    }

}
