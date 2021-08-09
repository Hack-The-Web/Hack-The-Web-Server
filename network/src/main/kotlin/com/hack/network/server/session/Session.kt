package com.hack.network.server.session

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.packets.GamePacketDecoder
import com.hack.api.network.packets.IncomingPacket
import com.hack.api.network.packets.PacketHandler
import com.hack.api.network.session.NetworkSession
import com.hack.game.api.login.LoginManager
import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.util.AttributeKey
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Session(private val ctx: ChannelHandlerContext) : NetworkSession, KoinComponent {

    private val loginManager: LoginManager<LoginInformation, NetworkSession> by inject()

    private val _incomingPackets = MutableSharedFlow<IncomingPacket>(extraBufferCapacity = 10)
    override val incomingPackets: SharedFlow<IncomingPacket> = _incomingPackets

    private val registeredPackets = mutableListOf<Job>()

    override fun <T> handlePacket(transformer: GamePacketDecoder<T>, action: suspend (T) -> Unit) {
        registeredPackets.add(packet(transformer, action))
    }

    override fun <T> handlePacket(transformer: GamePacketDecoder<T>, action: PacketHandler<T>) {
        registeredPackets.add(packet(transformer) { action.handlePacket(it) })
    }

    override fun handleIncomingPacket(packet: IncomingPacket) {
        _incomingPackets.tryEmit(packet)
    }

    override fun queueLogin(login: LoginInformation) {
        loginManager.initializeLogin(login, ctx.channel().session)
    }

    override fun isActive(): Boolean {
        return ctx.channel().isActive
    }

    override fun destroy() {
        registeredPackets.forEach { it.cancel() }
    }

    companion object {
        val SESSION_KEY = AttributeKey.valueOf<Session>("session")

        val Channel.session: Session get() = attr(SESSION_KEY).get()
    }
}