package com.leetcodetracker

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Data model for API response
data class LeetCodeStatsResponse(
    val status: String,
    val totalSolved: Int,
    val submissionCalendar: Map<String, Int>
)

// Retrofit interface
interface LeetCodeApiService {
    @GET("{username}")
    suspend fun getUserStats(@Path("username") username: String): LeetCodeStatsResponse

    companion object {
        val api: LeetCodeApiService by lazy {
            Retrofit.Builder()
                .baseUrl("https://leetcode-stats-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LeetCodeApiService::class.java)
        }
    }
}