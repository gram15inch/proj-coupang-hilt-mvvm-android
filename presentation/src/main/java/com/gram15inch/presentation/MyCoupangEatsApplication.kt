package com.gram15inch.presentation

import android.app.Application
import android.content.SharedPreferences
import com.gram15inch.mycoupangeats.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

class TimberDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        //return "${element.fileName}:${element.lineNumber}#${element.methodName}"
        return "#${element.methodName}"
    }
}

class PreferenceUtil(val prefs: SharedPreferences) {

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }
}


@HiltAndroidApp
class MyCoupangEatsApplication:Application() {
    companion object {

        lateinit var sSharedPreferences: SharedPreferences
        lateinit var prefs: PreferenceUtil
        // JWT Token Header 키 값
        val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(TimberDebugTree())
        }
        sSharedPreferences = applicationContext.getSharedPreferences(
            "SOFTSQUARED_TEMPLATE_APP", MODE_PRIVATE
        )
        prefs = PreferenceUtil(sSharedPreferences)


    }
}