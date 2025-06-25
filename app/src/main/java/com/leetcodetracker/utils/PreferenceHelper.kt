/*
package com.leetcodetracker.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
    private const val PREF_NAME = "leetprefs"
    private const val KEY_USERNAME = "leetcode_username"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getUsername(context: Context): String? {
        return getPrefs(context).getString(KEY_USERNAME, null)
    }

    fun setUsername(context: Context, username: String) {
        getPrefs(context).edit().putString(KEY_USERNAME, username).apply()
    }
}
*/

package com.leetcodetracker.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
    private const val PREF_NAME = "leetprefs"
    private const val KEY_USERNAME = "leetcode_username"
    private const val KEY_TOTAL_SOLVED = "cached_total"
    private const val KEY_TODAY_SOLVED = "cached_today"
    private const val KEY_STREAK = "cached_streak"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getUsername(context: Context): String? {
        return getPrefs(context).getString(KEY_USERNAME, null)
    }

    fun setUsername(context: Context, username: String) {
        getPrefs(context).edit().putString(KEY_USERNAME, username).apply()
    }

    fun setCachedStats(context: Context, totalSolved: Int, todaySolved: Int, streak: Int) {
        getPrefs(context).edit().apply {
            putInt(KEY_TOTAL_SOLVED, totalSolved)
            putInt(KEY_TODAY_SOLVED, todaySolved)
            putInt(KEY_STREAK, streak)
            apply()
        }
    }

    fun getCachedTotal(context: Context): Int {
        return getPrefs(context).getInt(KEY_TOTAL_SOLVED, 0)
    }

    fun getCachedToday(context: Context): Int {
        return getPrefs(context).getInt(KEY_TODAY_SOLVED, 0)
    }

    fun getCachedStreak(context: Context): Int {
        return getPrefs(context).getInt(KEY_STREAK, 0)
    }
}
