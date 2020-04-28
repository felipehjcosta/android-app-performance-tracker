package com.github.felipehjcosta.perf.metrics

interface MetricTracker<T : Any> {
    fun trackMetric(block: (T) -> Unit)
}