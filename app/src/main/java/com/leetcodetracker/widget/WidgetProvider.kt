package com.leetcodetracker.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.leetcodetracker.R

class WidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // Get cached values from SharedPreferences
        val prefs = context.getSharedPreferences("leetcode_prefs", Context.MODE_PRIVATE)
        val streak = prefs.getInt("cached_streak", 0)
        val totalSolved = prefs.getInt("cached_total", 0)
        val today = prefs.getInt("cached_today", 0)

        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_layout)

            views.setTextViewText(R.id.widget_text_streak, "🔥 $streak days")
            views.setTextViewText(R.id.widget_text_solved, "✅ $totalSolved")
            views.setTextViewText(R.id.widget_text_today, "📅 Today: $today")

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}