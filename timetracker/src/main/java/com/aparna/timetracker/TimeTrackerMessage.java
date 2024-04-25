package com.aparna.timetracker;

import android.content.Context;
import android.content.SharedPreferences;

public class TimeTrackerMessage {

    private static final String PREF_NAME = "TimeTrackerPrefs";
    private static final String KEY_START_TIME = "startTime";
    private static final String KEY_TOTAL_TIME = "totalTime";

    private SharedPreferences sharedPreferences;
    private long startTime;

    public TimeTrackerMessage(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        startTime = sharedPreferences.getLong(KEY_START_TIME, System.currentTimeMillis());
    }

    public void onStart() {
        startTime = System.currentTimeMillis();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(KEY_START_TIME, startTime);
        editor.apply();
    }

    public void onStop() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        long totalTime = sharedPreferences.getLong(KEY_TOTAL_TIME, 0);
        totalTime += elapsedTime;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(KEY_TOTAL_TIME, totalTime);
        editor.apply();
    }

    public String getTotalTimeSpent() {
        return String.valueOf(sharedPreferences.getLong(KEY_TOTAL_TIME, 0));
    }
}