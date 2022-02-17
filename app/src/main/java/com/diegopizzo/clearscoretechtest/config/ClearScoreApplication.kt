package com.diegopizzo.clearscoretechtest.config

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ClearScoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}