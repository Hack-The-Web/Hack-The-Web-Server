package com.hack.game.world

import com.hack.game.api.world.VirtualISP
import com.hack.game.api.world.device.VirtualSystem
import com.hack.game.api.world.entity.Character
import com.hack.game.api.world.entity.player.PlayerCharacter
import com.hack.game.world.device.VirtualSystemImpl
import kotlin.random.Random

class VirtualISPImpl : VirtualISP {

    private val _publicSystems = mutableMapOf<String, VirtualSystem>()
    private val _domains = mutableMapOf<String, String>()
    private val _registeredDomains = mutableListOf<String>()
    private val _owners = mutableMapOf<String, MutableList<VirtualSystem>>()

    override val publicSystems: Map<String, VirtualSystem>
        get() = _publicSystems
    override val domains: Map<String, String>
        get() = _domains
    override val registeredDomains: List<String>
        get() = _registeredDomains
    override val owners: Map<String, MutableList<VirtualSystem>>
        get() = _owners

    override fun setPublicAddress(system: VirtualSystem) {
        if(system.publicAddress != "127.0.0.1" && _publicSystems.containsKey(system.publicAddress)) {
            val old = system.publicAddress
            val new = generateNewIpAddress()
            _publicSystems.remove(old)
            _publicSystems[new] = system
        } else {
            val ip = generateNewIpAddress()
            _publicSystems[ip] = system
            system.publicAddress = ip
        }
    }

    override fun registerNewSystem(player: Character, name: String): VirtualSystem {
        val list = _owners.getOrPut(player.name) { mutableListOf() }
        val system = VirtualSystemImpl(player.name, name)
        setPublicAddress(system)
        list.add(system)
        return system
    }

    override fun registerDomain(domainName: String) {
        if(!_registeredDomains.contains(domainName)) {
            _registeredDomains.add(domainName)
        }
    }

    override fun linkDomain(domain: String, publicAddress: String) {
        _domains[domain] = publicAddress
    }

    override fun getDomainPublicAddress(domain: String): String {
        return _domains[domain] ?: "127.0.0.1"
    }

    override fun getVirtualSystems(player: Character): List<VirtualSystem> {
        return _owners[player.name] ?: listOf()
    }

    private fun generateNewIpAddress() : String {
        var address: String
        do {
            val part1 = Random.nextInt(0, 255)
            val part2 = Random.nextInt(0, 255)
            val part3 = Random.nextInt(0, 255)
            val part4 = Random.nextInt(0, 255)
            address = "$part1.$part2.$part3.$part4"
        } while(publicSystems.containsKey(address))
        return address
    }



}