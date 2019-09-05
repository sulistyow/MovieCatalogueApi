package com.sulistyo.moviecatalogueapi.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.helper.WidgetService
import com.sulistyo.moviecatalogueapi.ui.activity.DetailMoviesActivity

/**
 * Implementation of App Widget functionality.
 */
class StackWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        /* for (appWidgetId in appWidgetIds) {
             updateAppWidget(context, appWidgetManager, appWidgetId)
         }*/

        appWidgetIds.forEach {
            val intent = Intent(context, WidgetService::class.java)
            val view = RemoteViews(context.packageName, R.layout.stack_widget)
            view.setRemoteAdapter(R.id.stack_view, intent)
            view.setEmptyView(R.id.stack_view, R.id.empty_view)

            val detail = Intent(context, DetailMoviesActivity::class.java)
            val click =
                PendingIntent.getActivity(context, 0, detail, PendingIntent.FLAG_UPDATE_CURRENT)
            view.setPendingIntentTemplate(R.id.stack_view, click)

            val update = Intent(context, StackWidget::class.java)
            update.action = WIDGET_UPDATE
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                update,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            view.setOnClickPendingIntent(R.id.btRefresh, pendingIntent)

            appWidgetManager.updateAppWidget(it, view)

        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)

    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action!!.equals(WIDGET_UPDATE, ignoreCase = true)) {
            updateWidget(context)
        }

    }

    private fun updateWidget(context: Context?) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds =
            appWidgetManager.getAppWidgetIds(ComponentName(context!!, StackWidget::class.java))
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view)
    }

    companion object {
        const val WIDGET_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE"
        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {


        }
    }
}

