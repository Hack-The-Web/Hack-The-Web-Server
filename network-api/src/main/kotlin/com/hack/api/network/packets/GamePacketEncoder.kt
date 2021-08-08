package com.hack.api.network.packets

import java.io.DataOutputStream

fun interface GamePacketEncoder {

    fun DataOutputStream.encode()

}