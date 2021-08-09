package com.hack.api.network.session

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

object PacketDispatcher {
    private val executor = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    val Dispatchers.PACKETS: ExecutorCoroutineDispatcher
        get() = executor
}
