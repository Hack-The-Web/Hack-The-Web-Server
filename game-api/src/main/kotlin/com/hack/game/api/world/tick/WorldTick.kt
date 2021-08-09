package com.hack.game.api.world.tick

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

interface WorldTick : CoroutineScope {

    val tick: Flow<Long>
    val events: MutableMap<String, Job>

    fun subscribeEvent(event: WorldEvent)

    fun cancelEvent(id: String)

    fun shutdownGracefully()

}