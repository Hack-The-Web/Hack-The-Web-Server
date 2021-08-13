package com.hack.network.server.session

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.packets.*
import com.hack.api.network.session.NetworkSession
import com.hack.game.api.login.LoginManager
import com.hack.network.server.packets.OutgoingGamePacket
import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.util.AttributeKey
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class Session(private val ctx: ChannelHandlerContext) : NetworkSession, KoinComponent {

    private val loginManager: LoginManager<LoginInformation, NetworkSession> by inject()

    private val _incomingPackets = MutableSharedFlow<IncomingPacket>(extraBufferCapacity = 10)
    private val queueOutgoingPackets = ArrayDeque<OutgoingGamePacket>()
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

    override fun sendPacket(gamePacket: GamePacketEncoder) {
        val bytes = ByteArrayOutputStream()
        val data = DataOutputStream(bytes)
        val packet = OutgoingGamePacket(gamePacket.opcode, with(gamePacket) {
            data.encode()
            bytes.toByteArray()
        })
        ctx.writeAndFlush(packet)
    }

    override fun sendPackets() {
        val packets = queueOutgoingPackets.take(40)
        if(packets.isNotEmpty()) {
            packets.forEach { ctx.write(it) }
            queueOutgoingPackets.removeAll(packets)
            ctx.flush()
        }
    }


    companion object {
        val SESSION_KEY = AttributeKey.valueOf<Session>("session")

        val Channel.session: Session get() = attr(SESSION_KEY).get()
    }
}