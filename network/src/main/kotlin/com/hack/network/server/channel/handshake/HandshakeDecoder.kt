package com.hack.network.server.channel.handshake

import com.hack.api.network.handshake.HandshakeResponse
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class HandshakeDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        val opcode = buf.readUnsignedByte().toInt()
        if(opcode == LOGIN_OPCODE) {
            val revision = buf.readInt()
            println("Login request - Revision $revision")
            if(revision != REVISION) {
                ctx.close()
            }
            out.add(HandshakeResponse(1))
        }
    }

    companion object {
        const val LOGIN_OPCODE = 1
        const val UPDATE_OPCODE = 2

        const val REVISION = 1
    }
}