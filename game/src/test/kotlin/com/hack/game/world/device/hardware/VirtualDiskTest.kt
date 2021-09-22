package com.hack.game.world.device.hardware

import com.hack.game.world.device.software.CrackerSoftware
import org.junit.jupiter.api.Test


class VirtualDiskTest {

    @Test
    fun `adding software`() {
        val disk = VirtualDiskImpl()
        val cracker = CrackerSoftware("Basic Cracker")

        disk.addSoftware(cracker)

        assert(disk.hasSoftware(cracker.id())) { "Failed to add software." }

    }

    @Test
    fun `get the worst and best software versions`() {

        val disk = VirtualDiskImpl()
        val basicCracker = CrackerSoftware("Basic Cracker")
        val interCracker = CrackerSoftware("Intermediate Cracker").also {
            it.version = 5.0
        }
        val advancedCracker = CrackerSoftware("Advanced Cracker").also {
            it.version = 10.0
        }

        disk.addSoftware(basicCracker)
        disk.addSoftware(interCracker)
        disk.addSoftware(advancedCracker)

        val advCracker = disk.getBestSoftware("crc")

        assert(advancedCracker === advCracker) { "Failed to find best cracker." }

        val basCracker = disk.getWorstSoftware("crc")
        assert(basicCracker === basCracker) { "Failed to find worst cracker." }

    }

    @Test
    fun `capacity and usage`() {
        val disk = VirtualDiskImpl()
        val cracker = CrackerSoftware("Basic Cracker")

        disk.addSoftware(cracker)

        assert(disk.usage() == 10) { "Failed to use 10 MB of space." }

        val advCracker = CrackerSoftware("Advanced Cracker").also {
            it.version = 10.0
            it.size = 90
        }

        disk.addSoftware(advCracker)

        assert(disk.isFull()) { "Failed to fill virtual disk." }

    }

    @Test
    fun `deleting software`() {
        val disk = VirtualDiskImpl()
        val cracker = CrackerSoftware("Basic Cracker")

        disk.addSoftware(cracker)

        assert(disk.hasSoftware(cracker.id())) { "Failed to add cracker" }

        assert(disk.usage() == 10) { "Failed to calculate usage." }

        disk.deleteSoftware(cracker)

        assert(!disk.hasSoftware(cracker.id())) { "Failed to delete software" }

        assert(disk.usage() == 0) { "Failed to re-calculate usage." }

    }

}