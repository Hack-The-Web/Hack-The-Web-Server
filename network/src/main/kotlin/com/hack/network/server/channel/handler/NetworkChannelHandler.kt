package com.hack.network.server.channel.handler

import com.hack.api.network.handshake.HandshakeResponse
import com.hack.api.network.login.LoginInformation
import com.hack.api.network.login.LoginResponse
import com.hack.network.server.channel.login.LoginDecoder
import com.hack.network.server.channel.login.LoginEncoder
import com.hack.network.server.packets.PacketDecoder
import com.hack.network.server.packets.PacketEncoder
import com.hack.network.server.session.Session
import com.hack.network.server.session.Session.Companion.session
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import org.koin.core.component.KoinComponent

class NetworkChannelHandler : ChannelInboundHandlerAdapter(), KoinComponent {

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {

        ctx.writeAndFlush(msg)
        if(msg is HandshakeResponse && msg.value == 1) {
            ctx.pipeline().replace("decoder", "decoder", LoginDecoder())
            ctx.pipeline().replace("encoder", "encoder", LoginEncoder())
            ctx.channel().attr(Session.SESSION_KEY).set(Session(ctx))
        } else if(msg is LoginResponse && msg.loginResponse == 1) {
            ctx.channel().session.queueLogin(msg.loginInfo)
            ctx.pipeline().replace("decoder", "decoder", PacketDecoder())
            ctx.pipeline().replace("encoder", "encoder", PacketEncoder())
        }
    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        super.channelInactive(ctx)
        ctx.close()
    }

    override fun channelUnregistered(ctx: ChannelHandlerContext) {
        super.channelUnregistered(ctx)
        ctx.close()
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
    }
}