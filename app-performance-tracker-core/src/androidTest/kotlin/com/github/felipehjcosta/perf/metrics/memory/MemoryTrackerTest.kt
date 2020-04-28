package com.github.felipehjcosta.perf.metrics.memory

import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isGreaterThan
import assertk.assertions.isNotIn
import com.github.felipehjcosta.perf.runner.BackgroundRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MemoryTrackerTest {

    private val fakeBackgroundRunner = FakeBackgroundRunner()

    @Test(timeout = 10_000L)
    fun ensureTrackMemoryNotifiesMetric() {
        var currentMemoryMetric = MemoryMetric(0L)
        val oldMemoryMetrics = mutableSetOf<MemoryMetric>()

        Thread(Runnable {
            MemoryTracker(fakeBackgroundRunner).trackMetric { currentMemoryMetric = it }
        }).start()

        Thread.sleep(1_100L)

        (1..4).asSequence().forEach { _ ->
            assertThat(currentMemoryMetric).isNotIn(oldMemoryMetrics)
            assertThat(currentMemoryMetric.totalMemoryOfCurrentAppProcess).isGreaterThan(5_000_000L)
            oldMemoryMetrics.add(currentMemoryMetric)

            Thread.sleep(1_100L)
        }
    }

    class FakeBackgroundRunner : BackgroundRunner {

        override fun schedule(delayInMillis: Long, block: () -> Unit) {
            block()
            Thread.sleep(1_000L)
        }
    }
}