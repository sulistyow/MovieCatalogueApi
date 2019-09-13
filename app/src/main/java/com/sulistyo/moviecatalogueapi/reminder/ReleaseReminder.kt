package com.sulistyo.moviecatalogueapi.reminder

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.model.movie.kt.DataMovies
import com.sulistyo.moviecatalogueapi.model.movie.kt.ResponseMovie
import com.sulistyo.moviecatalogueapi.ui.activity.DetailMoviesActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ReleaseReminder : BroadcastReceiver() {
    private val TIME_FORMAT = "HH:mm"
    private val CHANNEL_ID = "Channel2"
    private val CHANNEL_NAME = "Release"
    private val GROUP_KEY = "key"
    private val ID_REMINDER = 101
    private var simpleDateFormat: SimpleDateFormat? = null
    private var currentDate: String? = null

    override fun onReceive(context: Context, intent: Intent) {
        simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        currentDate = simpleDateFormat!!.format(Date())

        ApiCall.instance().cekRelease(currentDate.toString(), currentDate.toString())
            .enqueue(object : Callback<ResponseMovie> {
                override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<ResponseMovie>,
                    response: Response<ResponseMovie>
                ) {
                    releaseCheck(response.body()?.results!!, context)
                }
            })

    }

    private fun releaseCheck(mData: List<DataMovies>, context: Context) {
        val newReleaseMovie = ArrayList<DataMovies>()

        for (detailMovie in mData) {
            if (detailMovie.releaseDate.equals(currentDate)) {
                newReleaseMovie.add(detailMovie)
            }
        }
        if (newReleaseMovie.size > 0) {
            sendNotification(newReleaseMovie, context)
        } else {
            showNotification(context)
        }
    }

    fun setRepeatingAlarm(context: Context, time: String, isStart: Boolean?) {
        if (isStart!!) {
            val startReminder: Long
            if (isDateInvalid(time, TIME_FORMAT)) return

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, ReleaseReminder::class.java)

            val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            val now = Calendar.getInstance()
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
            calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
            calendar.set(Calendar.SECOND, 0)

            if (calendar.timeInMillis <= now.timeInMillis)
                startReminder = calendar.timeInMillis + (AlarmManager.INTERVAL_DAY + 1)
            else
                startReminder = calendar.timeInMillis

            val pendingIntent = PendingIntent.getBroadcast(context, ID_REMINDER, intent, 0)
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                startReminder,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )

            Toast.makeText(context, context.resources.getString(R.string.daily), Toast.LENGTH_SHORT)
                .show()
        } else {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, ReleaseReminder::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, ID_REMINDER, intent, 0)

            alarmManager.cancel(pendingIntent)
            Toast.makeText(
                context,
                context.resources.getString(R.string.daily_of),
                Toast.LENGTH_SHORT
            )
                .show()

        }
    }

    private fun isDateInvalid(time: String, format: String): Boolean {
        return try {
            val dateFormat = SimpleDateFormat(format, Locale.getDefault())
            dateFormat.isLenient = false
            dateFormat.parse(time)
            false
        } catch (e: ParseException) {
            true
        }

    }

    private fun sendNotification(mData: List<DataMovies>, context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        for (i in 0..5) {

            val intent = Intent(context, DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.DATA, mData[i])
            intent.action = System.currentTimeMillis().toString()

            val pendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(mData[i].title)
                .setContentText(mData[i].title + context.resources.getString(R.string.release_new))
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentIntent(pendingIntent)
                .setGroup(GROUP_KEY)
                .setAutoCancel(true)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )

                builder.setChannelId(CHANNEL_ID)

                notificationManager.createNotificationChannel(channel)
            }

            val notification = builder.build()

            notificationManager.notify(i, notification)
        }

    }

    private fun showNotification(context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val ringtoneManager = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(context.getString(R.string.no_new_movie))
            .setContentText(context.getText(R.string.no_new_movie))
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 3000, 1000, 1000, 3000))
            .setSound(ringtoneManager)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 3000, 1000, 1000, 3000)

            builder.setChannelId(CHANNEL_ID)

            notificationManager.createNotificationChannel(channel)
        }

        val notification = builder.build()
        notificationManager.notify(ID_REMINDER, notification)
    }

}
