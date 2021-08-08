package com.hack.api.network.packets

interface OutgoingPacket {

    val opcode: Int
    val data: ByteArray

}