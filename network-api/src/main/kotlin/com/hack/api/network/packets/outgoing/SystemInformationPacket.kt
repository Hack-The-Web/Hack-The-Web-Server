package com.hack.api.network.packets.outgoing

import com.hack.api.network.packets.GamePacketEncoder
import java.io.DataOutputStream

class SystemInformationPacket(val displayName: String,
                              val diskUsage: Int,
                              val ramUsage: Int,
                              val cpuUsage: Int,
                              val networkCardUsage: Int,
                              val totalMoney: Int,
                              val totalBTC: Double,
                              val publicAddress: String) : GamePacketEncoder {

    override val opcode: Int = 1

    override fun DataOutputStream.encode() {
        writeUTF(displayName)
        writeInt(diskUsage)
        writeInt(ramUsage)
        writeInt(cpuUsage)
        writeInt(networkCardUsage)
        writeInt(totalMoney)
        writeDouble(totalBTC)
        writeUTF(publicAddress)
    }
}