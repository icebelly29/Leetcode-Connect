//package com.leetcodetracker.model
//
//import com.github.mikephil.charting.data.Entry
//
//data class UserStats(
//    val streak: Int,
//    val totalSolved: Int,
//    val todaySolved: Int,
//    val submissionHistory: List<Pair<Int, Int>> // e.g., (day, count)
//) {
//    fun generateStreakChartEntries(): List<Entry> {
//        return submissionHistory.map { (x, y) -> Entry(x.toFloat(), y.toFloat()) }
//    }
//}

package com.leetcodetracker.model

import com.github.mikephil.charting.data.Entry

data class UserStats(
    val streak: Int = 0, // this will be manually calculated
    val totalSolved: Int,
    val todaySolved: Int = 0, // this too
    val submissionCalendar: Map<String, Int> = emptyMap() // 👈 ADD THIS
) {
    fun generateStreakChartEntries(): List<Entry> {
        return submissionCalendar.entries
            .mapNotNull { (ts, count) ->
                ts.toLongOrNull()?.let { Entry(it.toFloat(), count.toFloat()) }
            }.sortedBy { it.x }
    }
}
