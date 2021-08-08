package com.hack.game.world.entity.player

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.session.NetworkSession
import com.hack.game.api.login.LoginManager
import com.hack.game.api.world.entity.player.PlayerCharacter
import org.koin.core.component.inject

class Player(override val name: String, val session: NetworkSession) : PlayerCharacter {

    private val loginManager: LoginManager<LoginInformation, NetworkSession> by inject()

    override fun logout() {
        loginManager.logoutQueue.add(this)
    }

    override fun isActive(): Boolean {
        return session.isActive()
    }

    override fun onTick(currentTick: Long) {

        println("Tick for $name - ${session.isActive()}")

    }


}