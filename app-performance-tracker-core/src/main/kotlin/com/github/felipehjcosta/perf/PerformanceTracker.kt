package com.github.felipehjcosta.perf

import com.github.felipehjcosta.perf.metrics.memory.MemoryMetric
import com.github.felipehjcosta.perf.metrics.memory.MemoryTracker
import com.github.felipehjcosta.perf.metrics.MetricTracker
import com.github.felipehjcosta.perf.runner.BackgroundHandlerRunner

object PerformanceTracker {

    private val observers = mutableSetOf<(MemoryMetric) -> Unit>()

    private lateinit var memoryTracker: MetricTracker<MemoryMetric>

    fun initialize(): PerformanceTracker {
        memoryTracker = MemoryTracker(BackgroundHandlerRunner())
        prepareToTrack()
        return this
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

infix fun PerformanceTracker.with(block: PerformanceTracker.() -> Unit) {
    this.block()
}

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
