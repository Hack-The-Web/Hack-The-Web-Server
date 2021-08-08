package com.hack.game.api.world.entity

import org.koin.core.component.KoinComponent

interface Character : KoinComponent {

    val name: String

    fun onTick(currentTick: Long)

}