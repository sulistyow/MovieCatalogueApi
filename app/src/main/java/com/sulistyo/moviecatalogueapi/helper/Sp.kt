package com.sulistyo.moviecatalogueapi.helper

import android.content.Context
import android.content.SharedPreferences

class Sp(context: Context) {
    private val PREF = "user"
    private var PRIVATE = 0
    private var pref: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private var context: Context? = null

    init {
        this.context = context
        pref = context.getSharedPreferences(PREF, PRIVATE)
        editor = pref?.edit()
    }

    fun movie(param: String) {
        editor!!.putString("movie", param)
        editor!!.commit()
    }

    fun movie(): String? {
        return pref?.getString("movie", "")
    }

    fun tv(param: String) {
        editor!!.putString("tv", param)
        editor!!.commit()
    }

    fun tv(): String? {
        return pref?.getString("tv", "")
    }

    fun state(param: String) {
        editor!!.putString("state", param)
        editor!!.commit()
    }

    fun state(): String? {
        return pref?.getString("state", "")
    }
}