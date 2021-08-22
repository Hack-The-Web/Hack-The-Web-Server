package com.hack.game.world.device.events

import com.hack.game.api.world.device.events.VirtualSystemEvent
import com.hack.game.api.world.device.events.VirtualSystemEventManager

class VirtualSystemEventManagerImpl : VirtualSystemEventManager {

    private val _systemEvents = mutableListOf<VirtualSystemEvent>()

    override val systemEvents: List<VirtualSystemEvent> = _systemEvents

    override fun fireEvent(event: VirtualSystemEvent) {
        _systemEvents.add(event)
    }

}