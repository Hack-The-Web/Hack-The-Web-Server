package com.hack.game.api.world.entity

import com.hack.game.api.world.entity.player.system.VirtualSystemManager
import org.koin.core.component.KoinComponent

interface Character : KoinComponent {

    val name: String

    val vsManager: VirtualSystemManager

    fun onTick(currentTick: Long)

}