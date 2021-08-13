package com.hack.api.network.packets.incoming

import com.hack.api.network.packets.GamePacketDecoder
import java.io.DataInputStream

class WidgetActionPacket(val tab: String, val actionId: String) {
    companion object : GamePacketDecoder<WidgetActionPacket> {
        override val opcode: Int = 2

        override fun DataInputStream.decodePacket(): WidgetActionPacket {
            return WidgetActionPacket(readUTF(), readUTF())
        }
    }
}