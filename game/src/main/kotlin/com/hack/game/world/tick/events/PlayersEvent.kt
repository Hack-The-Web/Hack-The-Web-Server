package com.hack.game.world.tick.events

import com.hack.game.api.world.entity.EntityManager
import com.hack.game.api.world.tick.WorldEvent
import org.koin.core.component.inject

class PlayersEvent : WorldEvent {

    val entityManager: EntityManager by inject()

    override val id: String = "players_tick"

    override fun onTick(currentTick: Long) {

        entityManager.players.forEach {
            if(it.isActive()) {
                it.onTick(currentTick)
            } else {
                it.logout()
            }
        }

    }
}