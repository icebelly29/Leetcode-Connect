/*
package com.leetcodetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.leetcodetracker.data.LeetCodeRepository
import com.leetcodetracker.utils.PreferenceHelper
import com.leetcodetracker.viewmodel.StatsViewModel
import com.leetcodetracker.viewmodel.StatsViewModelFactory
import android.content.Context
import android.content.IntentFilter
import android.appwidget.AppWidgetManager

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: StatsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvStreak: TextView = findViewById(R.id.tv_streak)
        val tvTotalSolved: TextView = findViewById(R.id.tv_total_solved)
        val tvTodaySolved: TextView = findViewById(R.id.tv_today_solved)
        val btnChart: Button = findViewById(R.id.btn_view_chart)
        val btnSettings: Button = findViewById(R.id.btn_settings)

        val repository = LeetCodeRepository()
        val factory = StatsViewModelFactory(repository, applicationContext)
        viewModel = ViewModelProvider(this, factory)[StatsViewModel::class.java]

        viewModel.streak.observe(this) { streak ->
            tvStreak.text = "Streak: $streak"
            cacheToPrefs("cached_streak", streak)
            updateWidget()
        }

        viewModel.totalSolved.observe(this) { total ->
            tvTotalSolved.text = "Total Solved: $total"
            cacheToPrefs("cached_total", total)
            updateWidget()
        }

        viewModel.todaySolved.observe(this) { today ->
            tvTodaySolved.text = "Today: $today"
            cacheToPrefs("cached_today", today)
            updateWidget()
        }

        viewModel.error.observe(this) {
            tvStreak.text = it
        }

        btnChart.setOnClickListener {
            startActivity(Intent(this, StreakChartActivity::class.java))
        }

        btnSettings.setOnClickListener {
            startActivity(Intent(this, UsernameActivity::class.java))
        }

        val username = PreferenceHelper.getUsername(this)
        if (!username.isNullOrEmpty()) {
            viewModel.loadStats()
        } else {
            tvStreak.text = "Set username in settings"
        }
    }

    private fun cacheToPrefs(key: String, value: Int) {
        val prefs = getSharedPreferences("leetcode_prefs", Context.MODE_PRIVATE)
        prefs.edit().putInt(key, value).apply()
    }

    private fun updateWidget() {
        val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
        intent.setPackage(packageName)
        sendBroadcast(intent)
    }
}
*/
package com.leetcodetracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.leetcodetracker.data.LeetCodeRepository
import com.leetcodetracker.utils.PreferenceHelper
import com.leetcodetracker.viewmodel.StatsViewModel
import com.leetcodetracker.viewmodel.StatsViewModelFactory
import android.appwidget.AppWidgetManager

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: StatsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvStreak: TextView = findViewById(R.id.tv_streak)
        val tvTotalSolved: TextView = findViewById(R.id.tv_total_solved)
        val tvTodaySolved: TextView = findViewById(R.id.tv_today_solved)
        val btnChangeUsername: Button = findViewById(R.id.btn_change_username)

        val repository = LeetCodeRepository()
        val factory = StatsViewModelFactory(repository, applicationContext)
        viewModel = ViewModelProvider(this, factory)[StatsViewModel::class.java]

        viewModel.streak.observe(this) { streak ->
            tvStreak.text = "Streak: $streak"
            cacheToPrefs("cached_streak", streak)
            updateWidget()
        }

        viewModel.totalSolved.observe(this) { total ->
            tvTotalSolved.text = "Total Solved: $total"
            cacheToPrefs("cached_total", total)
            updateWidget()
        }

        viewModel.todaySolved.observe(this) { today ->
            tvTodaySolved.text = "Today: $today"
            cacheToPrefs("cached_today", today)
            updateWidget()
        }

        viewModel.error.observe(this) {
            tvStreak.text = it
        }

        btnChangeUsername.setOnClickListener {
            startActivity(Intent(this, UsernameActivity::class.java))
        }

        val username = PreferenceHelper.getUsername(this)
        if (!username.isNullOrEmpty()) {
            viewModel.loadStats()
        } else {
            tvStreak.text = "Set username in settings"
        }
    }

    private fun cacheToPrefs(key: String, value: Int) {
        val prefs = getSharedPreferences("leetcode_prefs", Context.MODE_PRIVATE)
        prefs.edit().putInt(key, value).apply()
    }

    private fun updateWidget() {
        val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
        intent.setPackage(packageName)
        sendBroadcast(intent)
    }
}
