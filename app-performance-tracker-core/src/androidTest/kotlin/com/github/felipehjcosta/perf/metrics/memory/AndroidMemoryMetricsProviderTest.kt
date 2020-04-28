package com.github.felipehjcosta.perf.metrics.memory

import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertions.isGreaterThan
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidMemoryMetricsProviderTest {

    @Test
    fun testTotalMemoryOfCurrentProces() {
        assertk.assertThat(totalMemoryOfCurrentProcess()).isGreaterThan(0L)
    }
}