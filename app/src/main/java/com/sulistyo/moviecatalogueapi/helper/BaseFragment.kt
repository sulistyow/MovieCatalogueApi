package com.sulistyo.moviecatalogueapi.helper

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment() {
    var cd: CompositeDisposable? = null
    lateinit var data: Sp

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
        data = Sp(context!!)

    }
}