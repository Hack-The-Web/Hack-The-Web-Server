package com.hack.game.api.world.device.processes

import com.hack.game.api.world.device.VirtualSystem
import com.hack.game.api.world.device.software.VirtualSoftware
import kotlinx.coroutines.delay

interface VirtualProcess {

    val threads: Int
    val ram: Int
    val timeInSeconds: Long
    val spawner: VirtualSoftware
    var elapsedTime: Long
    var isRunning: Boolean

    fun onProcessStart() {}
    fun onProcessStop() {}

    fun startProcess(vs: VirtualSystem) {

        if(vs.ram.hasEnoughMemory(ram)) {
            if(vs.ram.allocateMemory(ram) && vs.cpu.allocateProcess(this)) {
                isRunning = true
                onProcessStart()
            }
        }

    }

    fun stopProcesss(vs: VirtualSystem) {
        vs.cpu.deallocateProcess(this)
        vs.ram.deallocateMemory(ram)
        isRunning = false
        onProcessStop()
    }

    suspend fun onTick(vs: VirtualSystem) {
        if (timeInSeconds > 0) {
            delay(1000)
            elapsedTime++
            if(elapsedTime >= timeInSeconds) {
                stopProcesss(vs)
            }
        }
    }

}