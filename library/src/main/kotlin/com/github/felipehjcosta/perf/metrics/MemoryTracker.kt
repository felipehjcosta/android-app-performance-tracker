package com.github.felipehjcosta.perf.metrics

import com.github.felipehjcosta.perf.runner.BackgroundRunner

class MemoryTracker(private val backgroundRunner: BackgroundRunner) : MetricTracker<MemoryMetric> {
    override fun trackMetric(block: (MemoryMetric) -> Unit) {
        backgroundRunner.schedule(1000L) {
            block(MemoryMetric(totalMemoryOfCurrentProcess()))
            trackMetric(block)
        }
    }

}