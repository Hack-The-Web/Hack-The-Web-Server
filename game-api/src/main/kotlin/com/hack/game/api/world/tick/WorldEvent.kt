package com.hack.game.api.world.tick

import org.koin.core.component.KoinComponent

interface WorldEvent : KoinComponent {

    val id: String

    fun onTick(currentTick: Long)

}