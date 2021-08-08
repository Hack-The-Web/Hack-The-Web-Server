package com.hack.api.network.session

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.packets.GamePacket

interface NetworkSession {

    fun sendPacket(packet: GamePacket)

    fun queueLogin(login: LoginInformation)

    fun isActive() : Boolean

    fun disconnect()

}