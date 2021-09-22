package com.hack.game.world.device.hardware

import com.hack.game.api.world.device.hardware.VirtualCPU
import com.hack.game.api.world.device.processes.VirtualProcess

class VirtualCPUImpl : VirtualCPU {

    private var capacity: Int = 2
    private val usedThreads = mutableMapOf<VirtualProcess, Int>()

    private val usage: Int
        get() = usedThreads.values.sum()

    override fun allocateProcess(process: VirtualProcess) : Boolean {
        val availableThreads = capacity - usage
        if(process.threads <= availableThreads) {
            usedThreads[process] = process.threads
        } else {
            usedThreads[process] = availableThreads
        }
        return true
    }

    override fun deallocateProcess(process: VirtualProcess): Boolean {
        usedThreads.remove(process)
        return !usedThreads.containsKey(process)
    }

    override fun processes(): List<VirtualProcess> {
        return usedThreads.keys.toList()
    }

    override fun getThreadUsage(process: VirtualProcess): Int {
        return usedThreads[process] ?: -1
    }

    override fun capacity(): Int {
        return capacity
    }

    override fun usage(): Int {
        return usage
    }

    override fun upgrade(cores: Int) {
        val threads = cores * 2
        capacity += threads
    }

    override fun damage(cores: Int) {
        val threads = cores * 2
        capacity -= threads
        if(capacity <= 0) {
            capacity = 2
        }
    }

}