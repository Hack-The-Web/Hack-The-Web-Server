package com.hack.game.api.world.tick

import org.koin.core.component.KoinComponent

interface WorldEvent : KoinComponent {

    val id: String

    suspend fun onTick(currentTick: Long)

}