package com.hack.game.api.world.device.hardware

import com.hack.game.api.world.device.processes.VirtualProcess

interface VirtualCPU {

    fun allocateProcess(process: VirtualProcess) : Boolean
    fun deallocateProcess(process: VirtualProcess) : Boolean
    fun processes(): List<VirtualProcess>

    fun getThreadUsage(process: VirtualProcess) : Int

    fun capacity(): Int
    fun usage(): Int
    fun upgrade(cores: Int)
    fun damage(cores: Int)

}