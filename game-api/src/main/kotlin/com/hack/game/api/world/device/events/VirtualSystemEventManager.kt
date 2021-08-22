package com.hack.game.api.world.device.events

interface VirtualSystemEventManager {

    val systemEvents: List<VirtualSystemEvent>

    fun fireEvent(event: VirtualSystemEvent)


}