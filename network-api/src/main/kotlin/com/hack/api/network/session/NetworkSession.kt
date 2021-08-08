package com.hack.api.network.session

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.packets.IncomingPacket

interface NetworkSession {

    fun handleIncomingPacket(packet: IncomingPacket)

    fun queueLogin(login: LoginInformation)

    fun isActive() : Boolean

    fun disconnect()

}