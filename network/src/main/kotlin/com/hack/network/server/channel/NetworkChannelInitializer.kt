package com.hack.network.server.channel

import com.hack.network.server.channel.handler.NetworkChannelHandler
import com.hack.network.server.channel.handshake.HandshakeDecoder
import com.hack.network.server.channel.handshake.HandshakeEncoder
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.nio.NioSocketChannel

class NetworkChannelInitializer : ChannelInitializer<NioSocketChannel>() {
    override fun initChannel(ch: NioSocketChannel) {
        println("Add these encoders/decoders.")
        ch.pipeline().addLast("decoder", HandshakeDecoder())
        ch.pipeline().addLast("encoder", HandshakeEncoder())
        ch.pipeline().addLast("handler", NetworkChannelHandler())
    }
}