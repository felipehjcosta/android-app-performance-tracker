package com.github.felipehjcosta.perf.console

import com.github.felipehjcosta.perf.PerformanceTracker

fun PerformanceTracker.logging(debuggable: Boolean = false) {
    if (debuggable) {
        this.registerOnUpdateMemoryMetrics {
            android.util.Log.i(
                    "PerformanceTracker",
                    "Total memory of the current process in ${it.totalMemoryOfCurrentAppProcessInMB} MB"
            )
        }
    }
}