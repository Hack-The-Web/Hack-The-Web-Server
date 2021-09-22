package com.hack.game.world.device

import com.hack.game.api.world.device.VirtualSystem
import com.hack.game.api.world.device.accounts.VirtualAccountManager
import com.hack.game.api.world.device.events.VirtualSystemEventManager
import com.hack.game.api.world.device.hardware.VirtualCPU
import com.hack.game.api.world.device.hardware.VirtualDisk
import com.hack.game.api.world.device.hardware.VirtualNetworkCard
import com.hack.game.api.world.device.hardware.VirtualRAM
import com.hack.game.world.device.accounts.VirtualAccountManagerImpl
import com.hack.game.world.device.accounts.root
import com.hack.game.world.device.events.VirtualSystemEventImpl
import com.hack.game.world.device.events.VirtualSystemEventManagerImpl
import com.hack.game.world.device.hardware.VirtualCPUImpl
import com.hack.game.world.device.hardware.VirtualDiskImpl
import com.hack.game.world.device.hardware.VirtualNetworkCardImpl
import com.hack.game.world.device.hardware.VirtualRAMImpl
import java.time.LocalDateTime

class VirtualSystemImpl(override var owner: String, override var name: String) : VirtualSystem {

    override val cpu: VirtualCPU = VirtualCPUImpl()
    override val disk: VirtualDisk = VirtualDiskImpl()
    override val networkCard: VirtualNetworkCard = VirtualNetworkCardImpl()
    override val ram: VirtualRAM = VirtualRAMImpl()

    override var publicAddress: String = "127.0.0.1"

    override val accountManager: VirtualAccountManager = VirtualAccountManagerImpl()
    override val systemEvents: VirtualSystemEventManager = VirtualSystemEventManagerImpl()

    override suspend fun onTick(currentTick: Long) {
        cpu.processes().forEach {
            it.onTick(this)
        }
    }

    override fun installSoftware(softwareId: String) {
        if (disk.hasSoftware(softwareId)) {
            val soft = disk.software(softwareId)
            soft.install().startProcess(this)
        }
    }

    override fun uninstallSoftware(softwareId: String) {
        if (disk.hasSoftware(softwareId)) {
            val soft = disk.software(softwareId)
            soft.uninstall().startProcess(this)
        }
    }

    override fun runSoftware(softwareId: String) {
        if(disk.hasSoftware(softwareId)) {
            val soft = disk.software(softwareId)
            soft.createExecutableProcess().startProcess(this)
        }
    }

    override fun loginFrom(username: String, password: String, publicAddress: String) {
        if(accountManager.login(username, password, publicAddress)) {
            systemEvents.fireEvent(VirtualSystemEventImpl(
                LocalDateTime.now(),
                accountManager.root(),
                "logged in from ${if(publicAddress == "127.0.0.1") "localhost" else publicAddress}"
            ))
        }
    }

}