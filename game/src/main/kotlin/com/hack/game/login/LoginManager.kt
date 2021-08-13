package com.hack.game.login

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.session.NetworkSession
import com.hack.game.api.login.LoginManager
import com.hack.game.api.world.entity.player.PlayerCharacter
import com.hack.game.world.entity.player.Player

class LoginManager : LoginManager<LoginInformation, NetworkSession> {

    override val loginQueue: ArrayDeque<PlayerCharacter> = ArrayDeque()
    override val logoutQueue: ArrayDeque<PlayerCharacter> = ArrayDeque()

    override fun initializeLogin(info: LoginInformation, session: NetworkSession) {
        println("Adding player ${info.username} to login queue")
        loginQueue.add(Player(info.username, session))
    }

    override fun validate(player: PlayerCharacter): Boolean {
        return player.name.lowercase() == "javatar"
    }
}