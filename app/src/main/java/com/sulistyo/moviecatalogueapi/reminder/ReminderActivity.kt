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
            val daily = "07:00"

            DailyService().setAlarm(this,daily, btDaily.isChecked)
        }

        btRelease.setOnClickListener {
            val release = "09:00"


        }

    }
}
