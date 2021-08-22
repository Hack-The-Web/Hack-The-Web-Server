package com.hack.network.server.packets

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import java.io.ByteArrayInputStream
import java.io.DataInputStream

class PacketDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        if(buf.readableBytes() >= 5) {
            val opcode = buf.readUnsignedByte().toInt()
            val size = buf.readInt()
            if(buf.readableBytes() >= size) {
                val bytes = ByteArray(size)
                buf.readBytes(bytes)
                out.add(IncomingGamePacket(opcode, DataInputStream(ByteArrayInputStream(bytes))))
            }
        }
    }
}