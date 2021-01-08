/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.hashim.recipeapp.BuildConfig
import com.hashim.recipeapp.Constants
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application() {

     val hIsdark = mutableStateOf(false)
    override fun onCreate() {
        super.onCreate()
        hInitTimber()

    }

    fun hToggleTheme() {
        hIsdark.value = !hIsdark.value
    }

    private fun hInitTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    super.log(priority, java.lang.String.format(Constants.hTag, tag), message, t)
                }
            })
        }
    }
}