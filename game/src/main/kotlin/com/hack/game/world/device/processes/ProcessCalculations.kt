package com.hack.game.world.device.processes

import com.hack.game.api.world.device.software.VirtualSoftware
import kotlin.math.pow
import kotlin.math.roundToInt

fun calculateRequiredThreads(seconds: Int): Int {
    val threads = seconds / 30
    return if(threads <= 0) 1 else threads
}

fun calculateSeconds(size: Int) : Int {
    return (0.05 * size).roundToInt()
}

fun calculateThreadTimeReduction(seconds: Int, threads: Int) : Int {
    val time = (seconds - (threads * 30))
    return if(time <= 0) 3 else time
}

fun calculateRAM(version: Double) : Int {
    return  1 + (10 * (version.pow(2)) / 4).roundToInt()
}