package com.hack.game.world.entity.player.system

import com.hack.game.api.world.VirtualISP
import com.hack.game.api.world.device.VirtualSystem
import com.hack.game.api.world.entity.Character
import com.hack.game.api.world.entity.player.system.VirtualSystemManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class VirtualSystemManagerImpl(val char: Character) : VirtualSystemManager, KoinComponent  {

    private val isp: VirtualISP by inject()

    override var mountedSystem: VirtualSystem? = null
        private set
    override var domainSystem: VirtualSystem? = null
        private set
    override var remoteSystem: VirtualSystem? = null
        private set

    override fun mount(name: String) {
        val systems = isp.getVirtualSystems(char)
        mountedSystem = if(systems.isEmpty()) {
            isp.registerNewSystem(char, name)
        } else {
            systems.find { it.name == name } ?: systems[0]
        }
    }

    override fun unmount() {
        mountedSystem = null
    }

    override fun connectByIP(publicAddress: String) {
        val system = isp.publicSystems[publicAddress]
        if(system != null) {
            remoteSystem = system
            domainSystem = null
        }
    }

    override fun connectByDomain(domain: String) {
        val publicAddress = isp.getDomainPublicAddress(domain)
        if(publicAddress != "127.0.0.1") {
            remoteSystem = null
            domainSystem = isp.publicSystems[publicAddress]
        }
    }
}