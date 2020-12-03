package com.diegopardo.transformersbattle.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import javax.inject.Inject

class PreferencesHelper @Inject constructor(context: Context) {

    private var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context)

    fun save(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun save(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun save(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    fun get(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getBoolean(key: String, default: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, default)
    }

    fun getLong(key: String, default: Long): Long {
        return sharedPreferences.getLong(key, default)
    }

    /**
     * Clears everything in the SharedPreferences.
     */
    fun clearSharedPrefs() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
