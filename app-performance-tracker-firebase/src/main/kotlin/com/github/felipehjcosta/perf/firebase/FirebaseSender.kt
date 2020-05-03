package com.github.felipehjcosta.perf.firebase

import android.os.Bundle
import com.github.felipehjcosta.perf.PerformanceTracker
import com.google.firebase.analytics.FirebaseAnalytics


fun firebaseLogging(firebaseAnalytics: FirebaseAnalytics): PerformanceTracker.() -> Unit {
    return {
        this.registerOnUpdateMemoryMetrics {
            val bundle = Bundle().apply {
                putLong("total_memory_process", it.totalMemoryOfCurrentAppProcess)
            }
            firebaseAnalytics.logEvent("performance_tracker", bundle)
        }
    }
}