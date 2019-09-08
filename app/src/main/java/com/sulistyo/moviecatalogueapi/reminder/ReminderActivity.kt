package com.sulistyo.moviecatalogueapi.reminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sulistyo.moviecatalogueapi.R
import kotlinx.android.synthetic.main.activity_reminder.*

class ReminderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)


        btDaily.setOnClickListener {
            val time = "07:00"

            DailyReminder().setAlarm(this,time, btDaily.isChecked)
        }

        btRelease.setOnClickListener {
            val time = "08:00"
            ReleaseReminder().setRepeatingAlarm(this, time, btDaily.isChecked)
        }

    }
}
