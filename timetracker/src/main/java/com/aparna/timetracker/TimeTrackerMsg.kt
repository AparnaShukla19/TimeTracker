package com.aparna.timetracker

import android.content.Context
import android.content.SharedPreferences


class TimeTrackerMsg(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("TimeTrackerPrefs", Context.MODE_PRIVATE)
    private var startTime: Long = sharedPreferences.getLong("startTime", System.currentTimeMillis())

    fun onStart() {
        startTime = System.currentTimeMillis()
        sharedPreferences.edit().putLong("startTime", startTime).apply()
    }

    fun onStop() {
        val currentTime = System.currentTimeMillis()
        val elapsedTime = currentTime - startTime

        var totalTime = sharedPreferences.getLong("totalTime", 0)
        totalTime += elapsedTime

        sharedPreferences.edit().putLong("totalTime", totalTime).apply()
    }

    fun getTotalTimeSpent(): Long {
        return sharedPreferences.getLong("totalTime", 0)
    }
}