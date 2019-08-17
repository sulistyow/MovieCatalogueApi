package com.sulistyo.moviecatalogueapi.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.data.model.MovieFavorite
import com.sulistyo.moviecatalogueapi.data.model.TvFavorite
import com.sulistyo.moviecatalogueapi.helper.BaseFragment
import com.sulistyo.moviecatalogueapi.ui.fragment.favorite.MovieFavAdapter
import com.sulistyo.moviecatalogueapi.ui.fragment.favorite.TvFavAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment() {

    private val listMovie: MutableList<MovieFavorite> = mutableListOf()
    private val listTv: MutableList<TvFavorite> = mutableListOf()

    private val mAdapter = MovieFavAdapter(listMovie)
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
                rvFavorite.apply {
                    adapter = mAdapter
                    layoutManager = LinearLayoutManager(this@FavoriteFragment.context)
                }
                getMovie()
            }
            1 -> {
                rvFavorite.apply {
                    adapter = tAdapter
                    layoutManager = LinearLayoutManager(this@FavoriteFragment.context)
                }
                getTv()
            }
        }
    }

    private fun getTv() {
        cd?.add(
            favorite.dataDao().getTvFav()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.isNotEmpty()) {
                        listTv.clear()
                        listTv.addAll(it)
                        tAdapter.notifyDataSetChanged()
                    }
                }
        )

    }

    private fun getMovie() {
        cd?.add(
            favorite.dataDao().getMovieFav()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.isNotEmpty()) {
                        listMovie.clear()
                        listMovie.addAll(it)
                        mAdapter.notifyDataSetChanged()
                    }
                }
        )
    }

}
