package com.github.felipehjcosta.perf.sample

import android.app.Application
import com.github.felipehjcosta.perf.PerformanceTracker
import com.github.felipehjcosta.perf.logging
import com.github.felipehjcosta.perf.with

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PerformanceTracker.initialize() with logging(BuildConfig.DEBUG)
    }
}