package com.hack.game.world.tick

import com.hack.game.api.world.tick.WorldEvent
import com.hack.game.api.world.tick.WorldTick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.CancellationException
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

class WorldTick : WorldTick, CoroutineScope {

    private val executor = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    override val coroutineContext: CoroutineContext = executor

    override val events: MutableMap<String, Job> = mutableMapOf()

    override val tick = flow {
        val duration = 100L
        var lastTick = 0L
        while(true) {
            val currentTick = if(lastTick < 0) duration else lastTick
            delay(currentTick)
            emit(currentTick)
            lastTick = duration - (System.currentTimeMillis() - lastTick)
        }
    }

    override fun subscribeEvent(event: WorldEvent) {
        val job = tick.onEach { event.onTick(it) }.launchIn(this)
        events[event.id] = job
    }

    override fun cancelEvent(id: String) {
        events[id]?.cancel()
    }

    override fun shutdownGracefully() {
        events.values.forEach { it.cancel(CancellationException("Shutting Down")) }
    }
}