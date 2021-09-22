package com.hack.game.world.device.hardware

import com.hack.game.api.world.device.hardware.VirtualDisk
import com.hack.game.api.world.device.software.VirtualSoftware

class VirtualDiskImpl : VirtualDisk {

    private val softwares = mutableMapOf<String, VirtualSoftware>()
    private var capacity: Int = 100
    private var usage: Int = 0


    override fun addSoftware(software: VirtualSoftware) {
        if((usage + software.size) <= capacity) {
            softwares[software.id()] = software
            usage += software.size
        }
    }

    override fun deleteSoftware(software: VirtualSoftware) {
        val id = software.id()
        if(softwares.containsKey(id)) {
            softwares.remove(id)
            usage -= software.size
        }
        if(usage < 0) {
            usage = 0
        }
    }

    override fun capacity(): Int {
        return capacity
    }

    override fun usage(): Int {
        return usage
    }

    override fun software(softwareId: String): VirtualSoftware {
        return softwares[softwareId]!!
    }

    override fun hasSoftware(softwareId: String): Boolean {
        return softwares.containsKey(softwareId)
    }

    override fun getBestSoftware(extension: String): VirtualSoftware {
        val softs = softwares.values.filter { it.extension == extension }.sortedBy { it.version }
        return softs.last()
    }

    override fun getWorstSoftware(extension: String): VirtualSoftware {
        val softs = softwares.values.filter { it.extension == extension}.sortedBy { it.version }
        return softs.first()
    }

    override fun upgrade(capacity: Int): Boolean {
        return false
    }

    override fun damage(capacity: Int): Boolean {
        return false
    }
}