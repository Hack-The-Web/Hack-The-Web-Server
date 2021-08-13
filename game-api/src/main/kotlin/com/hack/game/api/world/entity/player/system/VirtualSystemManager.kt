package com.hack.game.api.world.entity.player.system

import com.hack.game.api.world.device.VirtualSystem

interface VirtualSystemManager {

    val mountedSystem: VirtualSystem?
    val remoteSystem: VirtualSystem?
    val domainSystem: VirtualSystem?

    fun mount(name: String)
    fun unmount()

    fun connectByIP(publicAddress: String)
    fun connectByDomain(domain: String)

}