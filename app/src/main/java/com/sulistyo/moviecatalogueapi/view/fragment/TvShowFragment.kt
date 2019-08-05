package com.sulistyo.moviecatalogueapi.view.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.adapter.TvAdapter
import com.sulistyo.moviecatalogueapi.helper.BaseFragment
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.invisible
import com.sulistyo.moviecatalogueapi.view.activity.DetailActivity
import com.sulistyo.moviecatalogueapi.visible
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tv_show.*
import kotlinx.android.synthetic.main.layout_kosong.*

class TvShowFragment : BaseFragment() {
    private lateinit var mAdapter: TvAdapter

    companion object {
        fun newInstance(): MovieFragment {
            return MovieFragment()
        }
    }

    fun showEmptyData() {
        swipeRefresh.isRefreshing = false
        rvTv.invisible()
        kosong.visible()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh.isRefreshing = true
        getList()
        swipeRefresh.setOnRefreshListener {
            getList()
        }
    }

    override fun onResume() {
        super.onResume()
        getList()
    }


    fun getList() {
        cd?.add(
            ApiCall.instance().getTv(ApiCall.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    swipeRefresh.isRefreshing = false
                    if (it.isSuccessful) {
                        rvTv.visible()
                        kosong.invisible()
                        if (it.body()?.results!!.isNotEmpty()) {
                            mAdapter = TvAdapter(it.body()?.results!!) {

                                startActivity(Intent(activity, DetailActivity::class.java))
                            }
                            rvTv.apply {
                                layoutManager = GridLayoutManager(activity, 2)
                                adapter = mAdapter
                            }
                        }
                    } else showEmptyData()
                }, {
                    swipeRefresh.isRefreshing = false
                    showEmptyData()
                })
        )

    }

}
