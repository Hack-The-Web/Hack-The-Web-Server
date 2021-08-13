package com.hack.game.world.tick.events

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.session.NetworkSession
import com.hack.game.api.login.LoginManager
import com.hack.game.api.world.entity.EntityManager
import com.hack.game.api.world.tick.WorldEvent
import org.koin.core.component.inject

class LoginEvent : WorldEvent {

    private val loginManager: LoginManager<LoginInformation, NetworkSession> by inject()
    private val entityManager: EntityManager by inject()

    override val id: String = "login_event"

    override suspend fun onTick(currentTick: Long) {

        val logins = loginManager.loginQueue.take(40)

        logins.forEach {
            if(loginManager.validate(it)) {
                it.initialize()
                entityManager.players.add(it)
            }
        }

        loginManager.loginQueue.removeAll(logins)

        val logouts = loginManager.logoutQueue.take(40)

        logouts.forEach {
            entityManager.players.remove(it)
        }

        loginManager.logoutQueue.removeAll(logouts)
    }

}