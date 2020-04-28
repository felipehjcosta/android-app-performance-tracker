package com.github.felipehjcosta.perf

import android.os.SystemClock
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.doesNotContain
import assertk.assertions.isEqualTo
import assertk.assertions.isNotIn
import com.github.felipehjcosta.perf.metrics.MemoryMetric
import com.github.felipehjcosta.perf.metrics.MetricTracker
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PerformanceTrackerTest {

    @Test
    fun ensureNotify() {
        PerformanceTracker.registerOnUpdateMemoryMetrics {
            assertThat(it).isEqualTo(MemoryMetric(5_000_000L))
        }

        PerformanceTracker.initialize(FakeMemoryTracker())
    }

    class FakeMemoryTracker: MetricTracker<MemoryMetric> {
        override fun trackMetric(block: (MemoryMetric) -> Unit) {
            block(MemoryMetric(5_000_000L))
        }
    }
}