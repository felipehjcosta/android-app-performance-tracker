package com.github.felipehjcosta.perf.metrics

import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertions.isGreaterThan
import assertk.assertions.isNotZero
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidMemoryMetricsProviderTest {

    @Test
    fun testTotalMemoryOfCurrentProces() {
        assertk.assertThat(totalMemoryOfCurrentProcess()).isGreaterThan(0L)
    }
}