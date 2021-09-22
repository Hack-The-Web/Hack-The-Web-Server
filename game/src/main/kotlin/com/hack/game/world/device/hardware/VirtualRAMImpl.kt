package com.hack.game.world.device.hardware

import com.hack.game.api.world.device.hardware.VirtualRAM

class VirtualRAMImpl : VirtualRAM {

    private var capacity: Int = 1024
    private var usage: Int = 0

    override fun allocateMemory(mb: Int): Boolean {
        if((usage + mb) <= capacity) {
            usage += mb
            return true
        }
        return false
    }

    override fun deallocateMemory(mb: Int): Boolean {
        if(usage >= mb) {
            usage -= mb
            if(usage <= 0) {
                usage = 0
            }
            return true
        }
        return false
    }

    override fun hasEnoughMemory(mb: Int): Boolean {
        return (usage + mb) <= capacity
    }

    override fun capacity(): Int {
        return capacity
    }

    override fun usage(): Int {
        return usage
    }

    override fun upgrade(mb: Int) {
        capacity += mb
    }

    override fun damage(mb: Int) {
        capacity -= mb
        if(capacity <= 0) {
            capacity = 1024
        }
    }
}