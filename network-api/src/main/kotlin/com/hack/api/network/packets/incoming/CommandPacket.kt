package com.hack.api.network.packets.incoming

import com.hack.api.network.packets.GamePacketDecoder
import com.hack.api.network.packets.PacketHandler
import java.io.DataInputStream

class CommandPacket(val command : String) {
    companion object : GamePacketDecoder<CommandPacket> {
        override val opcode: Int = 1
        override fun DataInputStream.decodePacket(): CommandPacket {
            return CommandPacket(readUTF())
        }
    }
}