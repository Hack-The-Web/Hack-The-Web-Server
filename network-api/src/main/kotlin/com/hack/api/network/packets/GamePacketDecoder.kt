package com.hack.api.network.packets

import java.io.DataInputStream

fun interface GamePacketDecoder<T> {

    fun DataInputStream.decodePacket() : T

}