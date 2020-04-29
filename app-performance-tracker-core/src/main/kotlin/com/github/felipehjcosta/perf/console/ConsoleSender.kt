package com.github.felipehjcosta.perf.console

import com.github.felipehjcosta.perf.PerformanceTracker

fun logging(debuggable: Boolean = false): PerformanceTracker.() -> Unit {
    return {
        if (debuggable) {
            this.registerOnUpdateMemoryMetrics {
                android.util.Log.i(
                        "PerformanceTracker",
                        "Total memory of the current process in ${it.totalMemoryOfCurrentAppProcessInMB} MB"
                )
            }
        }
    }
}