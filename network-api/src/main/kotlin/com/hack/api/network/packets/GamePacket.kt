package com.hack.api.network.packets

import java.io.DataOutputStream

interface GamePacket {

    val opcode: Int

    fun DataOutputStream.encode()

}