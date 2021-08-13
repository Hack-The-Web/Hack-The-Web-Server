package com.hack.api.network.packets.incoming

import com.hack.api.network.packets.GamePacketDecoder
import java.io.DataInputStream

class OpenWidgetPacket(val tab: String) {
    companion object : GamePacketDecoder<OpenWidgetPacket> {
        override val opcode: Int = 3
        override fun DataInputStream.decodePacket(): OpenWidgetPacket {
            return OpenWidgetPacket(readUTF())
        }
    }
}