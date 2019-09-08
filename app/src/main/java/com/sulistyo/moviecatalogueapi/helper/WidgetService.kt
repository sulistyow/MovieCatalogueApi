package com.sulistyo.moviecatalogueapi.helper

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Binder
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.data.MovieFavorite
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.ui.activity.DetailMoviesActivity
import java.util.concurrent.ExecutionException

class WidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return StackRemoteViewsFactory()
    }

    inner class StackRemoteViewsFactory : RemoteViewsFactory {
        private var cursor: Cursor? = null
        var db: MovieFavorite? = null

        override fun onCreate() {

        }

        override fun getLoadingView(): RemoteViews? = null

        override fun getItemId(position: Int): Long = 0

        override fun onDataSetChanged() {
            if (cursor != null) {
                cursor!!.close()
            }
            val token = Binder.clearCallingIdentity()
            cursor = applicationContext.contentResolver.query(
                Uri.parse("content://com.sulistyo.moviecatalogueapi.provider/movie")
                , null, null, null, null
            )
            Binder.restoreCallingIdentity(token)
        }

        override fun hasStableIds(): Boolean = false

        override fun getViewAt(position: Int): RemoteViews {
            val remoteViews = RemoteViews(applicationContext.packageName, R.layout.item_widget)

            if (cursor != null) {
                cursor!!.moveToPosition(position)
            }
            try {
                cursor.let {
                    db = (it?.getString(3))?.toLong()?.let { it1 ->
                        MovieFavorite(
                            (it.getString(0)),
                            (it.getString(1)),
                            (it.getString(2)),
                            it1
                        )
                    }
                }
                remoteViews.setTextViewText(R.id.tvWidget, db?.title)
                val image = Glide.with(applicationContext)
                    .asBitmap()
                    .load(ApiCall.img + db?.posterPath)
                    .apply(RequestOptions().fitCenter())
                    .submit()
                    .get()
                remoteViews.setImageViewBitmap(R.id.ivWidget, image)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            }


            val intent = Intent()
            intent.putExtra(DetailMoviesActivity.STATE, "fav")
            intent.putExtra(DetailMoviesActivity.DATA, db)
            remoteViews.setOnClickFillInIntent(R.id.ivWidget, intent)

            return remoteViews
        }

        override fun getCount(): Int = cursor?.count ?: 0

        override fun getViewTypeCount(): Int = 1

        override fun onDestroy() {
            cursor!!.close()
        }

    }
}