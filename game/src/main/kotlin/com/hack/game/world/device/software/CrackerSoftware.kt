package com.hack.game.world.device.software

import com.hack.game.api.world.device.processes.VirtualProcess
import com.hack.game.api.world.device.software.VirtualSoftware
import com.hack.game.world.device.processes.*

class CrackerSoftware(override val name: String) : VirtualSoftware {

    override val extension: String = "crc"
    override var version: Double = 1.0
    override var size: Int = 10
    override var isInstalled: Boolean = false

    override fun install(): VirtualProcess {
        val seconds = calculateSeconds(size)
        val threads = calculateRequiredThreads(seconds)
        val timeInSeconds = calculateThreadTimeReduction(seconds, threads)
        val ram = calculateRAM(version)
        return InstallProcess(threads, timeInSeconds.toLong(), ram,this)
    }

    override fun uninstall(): VirtualProcess {
        val seconds = calculateSeconds(size)
        val threads = calculateRequiredThreads(seconds)
        val timeInSeconds = calculateThreadTimeReduction(seconds, threads)
        val ram = calculateRAM(version)
        return UninstallProcess(threads, timeInSeconds.toLong(), ram, this)
    }

    override fun createExecutableProcess(): VirtualProcess {
        return NoOperationProcess(spawner = this)
    }

}