package com.leetcodetracker.model

import com.google.gson.annotations.SerializedName

data class ApiUserStats(
    @SerializedName("totalSolved") val totalSolved: Int,
    @SerializedName("submissionCalendar") val submissionCalendar: Map<String, Int>
)
