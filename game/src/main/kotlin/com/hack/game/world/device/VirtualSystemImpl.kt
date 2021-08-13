package com.hack.game.world.device

import com.hack.game.api.world.device.VirtualSystem
import com.hack.game.api.world.device.accounts.VirtualAccountManager
import com.hack.game.api.world.device.hardware.VirtualCPU
import com.hack.game.api.world.device.hardware.VirtualDisk
import com.hack.game.api.world.device.hardware.VirtualNetworkCard
import com.hack.game.api.world.device.hardware.VirtualRAM
import com.hack.game.world.device.accounts.VirtualAccountManagerImpl
import com.hack.game.world.device.hardware.VirtualCPUImpl
import com.hack.game.world.device.hardware.VirtualDiskImpl
import com.hack.game.world.device.hardware.VirtualNetworkCardImpl
import com.hack.game.world.device.hardware.VirtualRAMImpl

class VirtualSystemImpl(override val owner: String, override var name: String) : VirtualSystem {

    override val cpu: VirtualCPU = VirtualCPUImpl()
    override val disk: VirtualDisk = VirtualDiskImpl()
    override val networkCard: VirtualNetworkCard = VirtualNetworkCardImpl()
    override val ram: VirtualRAM = VirtualRAMImpl()

    override var publicAddress: String = "127.0.0.1"

    override val accountManager: VirtualAccountManager = VirtualAccountManagerImpl()

}