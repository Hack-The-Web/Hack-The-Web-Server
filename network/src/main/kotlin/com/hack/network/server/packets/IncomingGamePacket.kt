package com.hack.network.server.packets

import com.hack.api.network.packets.IncomingPacket
import java.io.DataInputStream

class IncomingGamePacket(override val opcode: Int, override val data: DataInputStream) : IncomingPacket