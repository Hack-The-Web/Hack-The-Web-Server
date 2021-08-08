package com.hack.network.server.packets

import com.hack.api.network.packets.OutgoingPacket

class OutgoingGamePacket(override val opcode: Int, override val data: ByteArray) : OutgoingPacket