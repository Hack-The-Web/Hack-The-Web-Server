package com.hack.game.api.world.device.hardware

interface VirtualRAM {

    fun allocateMemory(mb: Int) : Boolean
    fun deallocateMemory(mb: Int) : Boolean
    fun hasEnoughMemory(mb: Int) : Boolean

    fun capacity(): Int
    fun usage(): Int

    fun upgrade(mb: Int)
    fun damage(mb: Int)

}