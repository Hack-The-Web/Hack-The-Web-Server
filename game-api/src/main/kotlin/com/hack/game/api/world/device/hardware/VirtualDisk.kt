package com.hack.game.api.world.device.hardware

import com.hack.game.api.world.device.software.VirtualSoftware

interface VirtualDisk {

    fun addSoftware(software: VirtualSoftware)
    fun deleteSoftware(software: VirtualSoftware)

    fun capacity(): Int
    fun usage() : Int

    fun isFull() : Boolean {
        return usage() == capacity()
    }

    fun software(softwareId: String): VirtualSoftware
    fun hasSoftware(softwareId: String): Boolean

    fun getBestSoftware(extension: String) : VirtualSoftware
    fun getWorstSoftware(extension: String) : VirtualSoftware

    fun upgrade(capacity: Int = 100) : Boolean
    fun damage(capacity: Int = 100) : Boolean
}