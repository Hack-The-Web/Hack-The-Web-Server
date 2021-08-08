package com.hack.game.api.world.tick

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

interface WorldTick {

    val tick: Flow<Long>
    val events: MutableMap<String, Job>

    fun subscribeEvent(event: WorldEvent)

    fun cancelEvent(id: String)

    fun shutdownGracefully()

}