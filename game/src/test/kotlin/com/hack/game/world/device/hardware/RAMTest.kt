package com.hack.game.world.device.hardware

import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.math.roundToInt

class RAMTest {

    fun `ram formula`() {

        val version = 100.0

        val ram = 1 + (10 * (version.pow(2)) / 4).roundToInt()

        println("RAM $ram")

    }

    @Test
    fun `add ram`() {

        val ram = VirtualRAMImpl()

        assert(ram.allocateMemory(4)) { "Failed to add ram" }

        assert(ram.usage() == 4) { "Failed to calc usage ram" }

    }

    @Test
    fun `remove ram`() {
        val ram = VirtualRAMImpl()

        assert(ram.allocateMemory(4)) { "Failed to allocate ram" }
        assert(ram.deallocateMemory(1)) { "Failed to de-allocate ram" }
        assert(ram.usage() == 3) { "Failed to calculate usage" }

    }

    @Test
    fun `add over capacity`() {
        val ram = VirtualRAMImpl()

        assert(!ram.allocateMemory(2000)) { "Failed to not allocate ram" }

        assert(ram.usage() == 0) { "Failed to calculate usage" }

    }

    @Test
    fun `remove under capacity`() {
        val ram = VirtualRAMImpl()

        assert(ram.allocateMemory(500)) { "Failed to allocate ram" }

        assert(!ram.deallocateMemory(2000)) { "Failed deallocate ram" }

    }

}