package com.sulistyo.moviecatalogueapi.reminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.*

class ReleaseService:BroadcastReceiver() {
    private val TIME_FORMAT = "HH:mm"
    private val CHANNEL_ID = "Channel2"
    private val CHANNEL_NAME = "Release"
    private val GROUP_KEY_RELEASE = "group_key_release"
    private val ID_REMINDER = 102
    internal var simpleDateFormat: SimpleDateFormat? = null
    internal var currentDate: String? = null


    override fun onReceive(p0: Context?, p1: Intent?) {
        simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        currentDate = simpleDateFormat!!.format(Date())
    }
}