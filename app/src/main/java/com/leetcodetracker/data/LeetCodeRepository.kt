
package com.leetcodetracker.data

import com.leetcodetracker.api.LeetCodeApiService
import com.leetcodetracker.model.ApiUserStats
import com.leetcodetracker.model.UserStats
import java.util.*

class LeetCodeRepository {
    suspend fun fetchUserStats(username: String): UserStats {
        val apiResponse = LeetCodeApiService.api.getUserStats(username)
        val calendar = apiResponse.submissionCalendar

        val secondsInDay = 86400L
        val now = System.currentTimeMillis() / 1000
        val todayMidnight = now - (now % secondsInDay)

        val todaySolved = calendar[todayMidnight.toString()] ?: 0

        // Calculate streak
        var streak = 0
        var currentDay = todayMidnight
        while (calendar.containsKey(currentDay.toString())) {
            streak++
            currentDay -= secondsInDay
        }

        return UserStats(
            totalSolved = apiResponse.totalSolved,
            todaySolved = todaySolved,
            streak = streak,
            submissionCalendar = calendar
        )
    }
}

//package com.leetcodetracker.data
//
//import com.leetcodetracker.api.LeetCodeApiService
//import com.leetcodetracker.model.UserStats
//import org.json.JSONObject
//import java.util.*
//
//class LeetCodeRepository {
//    suspend fun fetchUserStats(username: String): UserStats {
//        val response = LeetCodeApiService.api.getUserStats(username)
//
//        val totalSolved = response.totalSolved ?: 0
//
//        // Default values
//        var todaySolved = 0
//        var streak = 0
//
//        try {
//            val calendarJson = response.submissionCalendar?.toString() ?: "{}"
//            val calendar = JSONObject(calendarJson)
//
//            // Get current date
//            val today = Calendar.getInstance()
//            today.set(Calendar.HOUR_OF_DAY, 0)
//            today.set(Calendar.MINUTE, 0)
//            today.set(Calendar.SECOND, 0)
//            today.set(Calendar.MILLISECOND, 0)
//
//            // Epoch seconds at 00:00 today
//            val todayEpoch = today.timeInMillis / 1000
//
//            // Count today's submissions
//            if (calendar.has(todayEpoch.toString())) {
//                todaySolved = calendar.getInt(todayEpoch.toString())
//            }
//
//            // Calculate streak (past consecutive days with submissions)
//            var streakCount = 0
//            val tempCalendar = today.clone() as Calendar
//
//            while (true) {
//                val dayEpoch = tempCalendar.timeInMillis / 1000
//                if (calendar.has(dayEpoch.toString())) {
//                    streakCount++
//                } else {
//                    break
//                }
//                // Go to previous day
//                tempCalendar.add(Calendar.DATE, -1)
//            }
//
//            streak = streakCount
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        return UserStats(streak = streak, totalSolved = totalSolved, todaySolved = todaySolved)
//    }
//}
