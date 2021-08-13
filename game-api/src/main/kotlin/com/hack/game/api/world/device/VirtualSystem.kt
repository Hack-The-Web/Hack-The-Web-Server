package com.hack.game.api.world.device

import com.hack.game.api.world.device.accounts.VirtualAccountManager
import com.hack.game.api.world.device.hardware.VirtualCPU
import com.hack.game.api.world.device.hardware.VirtualDisk
import com.hack.game.api.world.device.hardware.VirtualNetworkCard
import com.hack.game.api.world.device.hardware.VirtualRAM

interface VirtualSystem {

    val owner: String
    var name: String
    var publicAddress: String
    val disk: VirtualDisk
    val ram: VirtualRAM
    val cpu: VirtualCPU
    val networkCard: VirtualNetworkCard

    val accountManager: VirtualAccountManager

}