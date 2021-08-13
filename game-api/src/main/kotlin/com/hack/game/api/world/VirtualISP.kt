package com.hack.game.api.world

import com.hack.game.api.world.device.VirtualSystem
import com.hack.game.api.world.entity.Character
import com.hack.game.api.world.entity.player.PlayerCharacter

interface VirtualISP {

    val publicSystems: Map<String, VirtualSystem>
    val domains: Map<String, String>
    val registeredDomains: List<String>

    val owners: Map<String, List<VirtualSystem>>

    fun setPublicAddress(system: VirtualSystem)
    fun registerNewSystem(player: Character, name: String) : VirtualSystem

    fun registerDomain(domainName: String)
    fun linkDomain(domain: String, publicAddress: String)

    fun getDomainPublicAddress(domain: String) : String

    fun getVirtualSystems(player: Character) : List<VirtualSystem>

}