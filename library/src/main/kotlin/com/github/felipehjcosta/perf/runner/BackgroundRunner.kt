package com.github.felipehjcosta.perf.runner

interface BackgroundRunner {
    fun schedule(delayInMillis: Long, block: () -> Unit)
}