package com.hack.network.server.channel

import com.hack.network.server.channel.handler.NetworkChannelHandler
import com.hack.network.server.channel.handshake.HandshakeDecoder
import com.hack.network.server.channel.handshake.HandshakeEncoder
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.timeout.IdleStateHandler

class NetworkChannelInitializer : ChannelInitializer<NioSocketChannel>() {
    override fun initChannel(ch: NioSocketChannel) {
        ch.pipeline().addLast("decoder", HandshakeDecoder())
        ch.pipeline().addLast("encoder", HandshakeEncoder())
        ch.pipeline().addLast("idle", IdleStateHandler(60, 0, 60))
        ch.pipeline().addLast("handler", NetworkChannelHandler())
    }
}