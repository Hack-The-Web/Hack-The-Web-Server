package com.hack.game.world.tick.events

import com.hack.game.api.world.entity.EntityManager
import com.hack.game.api.world.tick.WorldEvent
import kotlinx.coroutines.delay
import org.koin.core.component.inject

class DebugEvent(override val id: String) : WorldEvent {

    private val entityManager: EntityManager by inject()

    override suspend fun onTick(currentTick: Long) {
        delay(10000)

        println("Online Players: ${entityManager.players.size}")

        println("Current Tick: $currentTick")

    }
}