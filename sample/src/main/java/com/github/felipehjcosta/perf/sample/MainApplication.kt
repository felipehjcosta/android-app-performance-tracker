package com.github.felipehjcosta.perf.sample

import android.app.Application
import com.github.felipehjcosta.perf.PerformanceTracker
import com.github.felipehjcosta.perf.logTracker

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PerformanceTracker.logTracker()
        PerformanceTracker.initialize()
    }
}