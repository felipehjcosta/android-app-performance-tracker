package com.github.felipehjcosta.perf

import com.github.felipehjcosta.perf.metrics.MemoryMetric
import com.github.felipehjcosta.perf.metrics.MetricTracker

object PerformanceTracker {

    private val observers = mutableSetOf<(MemoryMetric) -> Unit>()

    private lateinit var memoryTracker: MetricTracker<MemoryMetric>

    internal fun initialize(memoryTracker: MetricTracker<MemoryMetric>) {
        this.memoryTracker = memoryTracker.apply {
            trackMetric { memoryMetric ->
                observers.forEach { listener -> listener(memoryMetric) }
            }
        }
    }

    fun registerOnUpdateMemoryMetrics(listener: (MemoryMetric) -> Unit) {
        observers.add(listener)
    }

}
