package com.hack.api.network.session

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.packets.*
import com.hack.api.network.session.PacketDispatcher.PACKETS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

interface NetworkSession {

    val incomingPackets: SharedFlow<IncomingPacket>

    fun handleIncomingPacket(packet: IncomingPacket)

    fun queueLogin(login: LoginInformation)

    fun isActive(): Boolean

    fun destroy()

    fun sendPacket(gamePacket: GamePacketEncoder)

    fun sendPackets()

    fun <T> handlePacket(transformer: GamePacketDecoder<T>, action: suspend (T) -> Unit)
    fun <T> handlePacket(transformer: GamePacketDecoder<T>, action: PacketHandler<T>)

    fun <T> packet(transformer: GamePacketDecoder<T>, action: suspend (T) -> Unit) = incomingPackets
        .filter { it.opcode == transformer.opcode }
        .transform {
            with(transformer) {
                emit(it.data.decodePacket())
            }
        }
        .flowOn(Dispatchers.IO)
        .onEach { action(it) }
        .launchIn(CoroutineScope(Dispatchers.PACKETS))

}