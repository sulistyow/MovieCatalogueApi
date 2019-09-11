package com.sulistyo.moviecatalogueapi.reminder

import android.os.Bundle
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.helper.BaseActivity
import kotlinx.android.synthetic.main.activity_reminder.*

class ReminderActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        check()

        btDaily.setOnClickListener {
            sharedPref.daily = btDaily.isChecked
            DailyReminder().setAlarm(this, "07:00", btDaily.isChecked)
        }

        btRelease.setOnClickListener {
            sharedPref.release = btRelease.isChecked
            ReleaseReminder().setRepeatingAlarm(this, "08:00", btDaily.isChecked)
        }

    }

    private fun check() {
        btDaily.isChecked = sharedPref.daily
        btRelease.isChecked = sharedPref.release
    }
}
