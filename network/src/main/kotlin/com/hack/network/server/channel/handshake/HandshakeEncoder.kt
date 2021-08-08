package com.hack.network.server.channel.handshake

import com.hack.api.network.handshake.HandshakeResponse
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class HandshakeEncoder : MessageToByteEncoder<HandshakeResponse>() {
    override fun encode(ctx: ChannelHandlerContext, msg: HandshakeResponse, out: ByteBuf) {
        out.writeByte(msg.value)
    }
}