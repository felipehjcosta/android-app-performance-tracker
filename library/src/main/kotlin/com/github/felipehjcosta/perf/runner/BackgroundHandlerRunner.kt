package com.github.felipehjcosta.perf.runner

import android.os.Handler
import android.os.HandlerThread
import android.os.Process

private const val BACKGROUND_HANDLER_THREAD_NAME = "BackgroundRunnerName"

class BackgroundHandlerRunner : BackgroundRunner {
    private val backgroundHandler: Handler by lazy {
        val backgroundHandlerThread = HandlerThread(
                BACKGROUND_HANDLER_THREAD_NAME,
                Process.THREAD_PRIORITY_BACKGROUND
        ).apply { start() }

        Handler(backgroundHandlerThread.looper)
    }

    override fun schedule(delayInMillis: Long, block: () -> Unit) {
        backgroundHandler.postDelayed(object : Runnable {
            override fun run() {
                block()
            }
        }, delayInMillis)
    }
}