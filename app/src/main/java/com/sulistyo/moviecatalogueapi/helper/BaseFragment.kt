package com.sulistyo.moviecatalogueapi.helper

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sulistyo.moviecatalogueapi.data.Favorite
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment() {
    var cd: CompositeDisposable? = null
    lateinit var favorite: Favorite

    override fun onDestroy() {
        super.onDestroy()
        cd!!.clear()
    }

    fun checkDisposable() {
        if (cd != null) {
            cd!!.clear()
            cd = CompositeDisposable()
        } else cd = CompositeDisposable()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkDisposable()
        favorite = Favorite.getInstance(this!!.activity!!)!!
    }
}