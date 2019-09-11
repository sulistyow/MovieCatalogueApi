package com.sulistyo.moviecatalogueapi.helper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sulistyo.moviecatalogueapi.data.FavoriteDb
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast

open class BaseActivity : AppCompatActivity() {
    var cd: CompositeDisposable? = null
    var favoriteDb: FavoriteDb? = null
    lateinit var sharedPref: SharedPreference

    private fun checkDisposable() {
        cd = if (cd != null) {
            cd!!.clear()
            CompositeDisposable()
        } else CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoriteDb = FavoriteDb.getInstance(this)!!
        checkDisposable()
        sharedPref = SharedPreference(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        cd?.clear()
        FavoriteDb.destroyInstance()
    }

    fun deleteMovieFav(title: String) {
        cd?.add(
            Observable.fromCallable { favoriteDb?.dataDao()?.deleteMovie(title) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { toast("Removed from favorites") }
        )
    }

    fun deleteTvFav(name: String) {
        cd?.add(
            Observable.fromCallable { favoriteDb?.dataDao()?.deleteTv(name) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { toast("Removed from favorites") }
        )
    }
}