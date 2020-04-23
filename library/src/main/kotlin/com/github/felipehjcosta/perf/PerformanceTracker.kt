package com.github.felipehjcosta.perf

import com.github.felipehjcosta.perf.metrics.MemoryMetric
import com.github.felipehjcosta.perf.metrics.MemoryTracker
import com.github.felipehjcosta.perf.metrics.MetricTracker
import com.github.felipehjcosta.perf.runner.BackgroundHandlerRunner

object PerformanceTracker {

    private val observers = mutableSetOf<(MemoryMetric) -> Unit>()

    private lateinit var memoryTracker: MetricTracker<MemoryMetric>

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
