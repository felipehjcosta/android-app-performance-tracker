package com.github.felipehjcosta.perf

import com.github.felipehjcosta.perf.metrics.MemoryMetric
import com.github.felipehjcosta.perf.metrics.MemoryTracker
import com.github.felipehjcosta.perf.metrics.MetricTracker
import com.github.felipehjcosta.perf.runner.BackgroundHandlerRunner

object PerformanceTracker {

    private val observers = mutableSetOf<(MemoryMetric) -> Unit>()

    private lateinit var memoryTracker: MetricTracker<MemoryMetric>

    fun initialize() {
        memoryTracker = MemoryTracker(BackgroundHandlerRunner())
        prepareToTrack()
    }

    internal fun initialize(memoryTracker: MetricTracker<MemoryMetric>) {
        this.memoryTracker = memoryTracker
        prepareToTrack()
    }

    private fun prepareToTrack() {
        memoryTracker.trackMetric(this::notifyObservers)
    }

    private fun notifyObservers(memoryMetric: MemoryMetric) {
        observers.forEach { listener -> listener(memoryMetric) }
    }

    fun registerOnUpdateMemoryMetrics(listener: (MemoryMetric) -> Unit) {
        observers.add(listener)
    }

}

fun PerformanceTracker.logTracker(debuggable: Boolean = false) {
    if (debuggable) {
        this.registerOnUpdateMemoryMetrics {
            android.util.Log.i(
                    "PerformanceTracker",
                    "Total memory of the current process in ${it.totalMemoryOfCurrentAppProcessInMB} MB"
            )
        }
    }
}
