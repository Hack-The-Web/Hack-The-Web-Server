package com.hack.api.network.packets.outgoing

import com.hack.api.network.packets.GamePacketEncoder
import com.hack.api.network.packets.buffer.BufferExtensions.writeString
import java.io.DataOutputStream
import java.time.LocalDateTime
import java.time.ZoneId

class SystemEventPacket(val date: LocalDateTime, val slot: Int, val username: String, val msg: String) : GamePacketEncoder {
    override val opcode: Int = 2
    override fun DataOutputStream.encode() {
        val zone = ZoneId.of("UTC")
        val stamp = date.atZone(zone).toEpochSecond()
        writeLong(stamp)
        writeInt(slot)
        writeString(username)
        writeString(msg)
    }
}