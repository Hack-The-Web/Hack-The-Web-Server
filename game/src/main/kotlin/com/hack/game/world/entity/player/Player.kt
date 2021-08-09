package com.hack.game.world.entity.player

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.packets.incoming.CommandPacket
import com.hack.api.network.session.NetworkSession
import com.hack.game.api.login.LoginManager
import com.hack.game.api.world.entity.player.PlayerCharacter
import com.hack.game.api.world.entity.player.command.CommandRepository
import com.hack.game.api.world.tick.WorldTick
import com.hack.game.world.entity.player.commands.CommandHandler
import com.hack.game.world.entity.player.commands.CommandRepositoryImpl
import org.koin.core.component.inject

class Player(override val name: String, val session: NetworkSession) : PlayerCharacter {

    private val loginManager: LoginManager<LoginInformation, NetworkSession> by inject()

    override fun logout() {
        session.destroy()
        loginManager.logoutQueue.add(this)
    }

    override fun isActive(): Boolean {
        return session.isActive()
    }

    override fun initialize() {
        session.handlePacket(CommandPacket, CommandHandler(this))
    }

    override fun onTick(currentTick: Long) {}
}