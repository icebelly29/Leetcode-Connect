/*
package com.leetcodetracker.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.leetcodetracker.data.LeetCodeRepository
import com.leetcodetracker.utils.PreferenceHelper
import kotlinx.coroutines.launch

class StatsViewModel(
    private val repository: LeetCodeRepository,
    private val context: Context
) : ViewModel() {

    private val _streak = MutableLiveData<Int>()
    val streak: LiveData<Int> = _streak

    private val _totalSolved = MutableLiveData<Int>()
    val totalSolved: LiveData<Int> = _totalSolved

    private val _todaySolved = MutableLiveData<Int>()
    val todaySolved: LiveData<Int> = _todaySolved

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadStats() {
        val username = PreferenceHelper.getUsername(context)
        if (username.isNullOrEmpty()) {
            _error.postValue("No username set. Please set your LeetCode username.")
            return
        }

        viewModelScope.launch {
            try {
                val stats = repository.fetchUserStats(username)
                _streak.postValue(stats.streak)
                _totalSolved.postValue(stats.totalSolved)
                _todaySolved.postValue(stats.todaySolved)
            } catch (e: Exception) {
                _error.postValue("Failed to load stats: ${e.message}")
            }
        }
    }
}
*/
package com.leetcodetracker.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.leetcodetracker.data.LeetCodeRepository
import com.leetcodetracker.utils.PreferenceHelper
import kotlinx.coroutines.launch

class StatsViewModel(
    private val repository: LeetCodeRepository,
    private val context: Context
) : ViewModel() {

    private val _streak = MutableLiveData<Int>()
    val streak: LiveData<Int> = _streak

    private val _totalSolved = MutableLiveData<Int>()
    val totalSolved: LiveData<Int> = _totalSolved

    private val _todaySolved = MutableLiveData<Int>()
    val todaySolved: LiveData<Int> = _todaySolved

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadStats() {
        val username = PreferenceHelper.getUsername(context)
        if (username.isNullOrEmpty()) {
            _error.postValue("No username set. Please set your LeetCode username.")
            return
        }

        viewModelScope.launch {
            try {
                val stats = repository.fetchUserStats(username)

                // Post to LiveData
                _streak.postValue(stats.streak)
                _totalSolved.postValue(stats.totalSolved)
                _todaySolved.postValue(stats.todaySolved)

                // Save to SharedPreferences
                PreferenceHelper.setCachedStats(
                    context,
                    totalSolved = stats.totalSolved,
                    todaySolved = stats.todaySolved,
                    streak = stats.streak
                )

            } catch (e: Exception) {
                _error.postValue("Failed to load stats: ${e.message}")
            }
        }
    }
}
