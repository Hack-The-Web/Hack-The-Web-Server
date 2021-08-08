package com.hack.network.server.session

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.packets.IncomingPacket
import com.hack.api.network.session.NetworkSession
import com.hack.game.api.login.LoginManager
import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.util.AttributeKey
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Session(private val ctx: ChannelHandlerContext) : NetworkSession, KoinComponent {

    private val loginManager: LoginManager<LoginInformation, NetworkSession> by inject()



    override fun handleIncomingPacket(packet: IncomingPacket) {
        TODO("Not yet implemented")
    }

    override fun queueLogin(login: LoginInformation) {
        loginManager.initializeLogin(login, ctx.channel().session)
    }

    override fun isActive(): Boolean {
        return ctx.channel().isActive
    }

    override fun disconnect() {
        ctx.disconnect()
    }

    companion object {
        val SESSION_KEY = AttributeKey.valueOf<Session>("session")

        val Channel.session: Session get() = attr(SESSION_KEY).get()
    }
}