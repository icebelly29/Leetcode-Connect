package com.leetcodetracker.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.leetcodetracker.model.ApiUserStats

interface LeetCodeApiService {
    @GET("{username}")
    suspend fun getUserStats(@Path("username") username: String): ApiUserStats

    companion object {
        private const val BASE_URL = "https://leetcode-stats-api.herokuapp.com/"

        val api: LeetCodeApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LeetCodeApiService::class.java)
        }
    }
}