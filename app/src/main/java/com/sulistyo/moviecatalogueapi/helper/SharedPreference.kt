package com.sulistyo.moviecatalogueapi.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {

    companion object {
        val File = "file"
        val DAILY = "daily"
        val RELEASE = "rilis"
    }

    val sharedPref: SharedPreferences = context.getSharedPreferences(File, 0)

    var daily: Boolean
        get() = sharedPref.getBoolean(DAILY, false)
        set(value) = sharedPref.edit().putBoolean(DAILY, value).apply()

    var release: Boolean
        get() = sharedPref.getBoolean(RELEASE, false)
        set(value) = sharedPref.edit().putBoolean(RELEASE, value).apply()

}