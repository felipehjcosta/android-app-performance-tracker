package com.github.felipehjcosta.perf.metrics.memory

import android.os.Debug


fun totalMemoryOfCurrentProcess(): Long {
    return totalMemoryOfCurrentProcessInkB().toBytes()
}

fun totalMemoryOfCurrentProcessInkB(): Long {
    val memInfo = Debug.MemoryInfo()
    Debug.getMemoryInfo(memInfo)
    var totalMemoryOfCurrentAppProcess = memInfo.totalPrivateDirty.toLong()
    totalMemoryOfCurrentAppProcess += memInfo.totalPrivateClean.toLong()
    return totalMemoryOfCurrentAppProcess
}

private fun Long.toBytes(): Long = this * 1024L