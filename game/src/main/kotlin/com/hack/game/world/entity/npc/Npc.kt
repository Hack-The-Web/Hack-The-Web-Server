package com.hack.game.world.entity.npc

import com.hack.game.api.world.entity.npc.NpcCharacter
import com.hack.game.api.world.entity.player.system.VirtualSystemManager
import com.hack.game.world.entity.player.system.VirtualSystemManagerImpl

open class Npc(override val name: String) : NpcCharacter {

    override val vsManager: VirtualSystemManager = VirtualSystemManagerImpl(this)

    override fun onTick(currentTick: Long) {

    }

}