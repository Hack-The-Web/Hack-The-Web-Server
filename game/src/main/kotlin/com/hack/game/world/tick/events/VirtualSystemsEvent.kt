package com.hack.game.world.tick.events

import com.hack.game.api.world.VirtualISP
import com.hack.game.api.world.tick.WorldEvent
import org.koin.core.component.inject

class VirtualSystemsEvent : WorldEvent {

    private val isp: VirtualISP by inject()

    override val id: String = "virtual_systems_event"

    override suspend fun onTick(currentTick: Long) {

        isp.publicSystems.values.forEach { it.onTick(currentTick) }

    }

}