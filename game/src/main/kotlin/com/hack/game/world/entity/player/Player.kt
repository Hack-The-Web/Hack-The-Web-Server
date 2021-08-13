package com.hack.game.world.entity.player

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.packets.incoming.CommandPacket
import com.hack.api.network.packets.incoming.OpenWidgetPacket
import com.hack.api.network.packets.incoming.WidgetActionPacket
import com.hack.api.network.packets.outgoing.SystemInformationPacket
import com.hack.api.network.session.NetworkSession
import com.hack.game.api.login.LoginManager
import com.hack.game.api.world.entity.player.PlayerCharacter
import com.hack.game.api.world.entity.player.system.VirtualSystemManager
import com.hack.game.api.world.entity.player.widget.WidgetManager
import com.hack.game.world.entity.player.commands.CommandHandler
import com.hack.game.world.entity.player.system.VirtualSystemManagerImpl
import com.hack.game.world.entity.player.widget.OpenWidgetHandler
import com.hack.game.world.entity.player.widget.WidgetActionPacketHandler
import com.hack.game.world.entity.player.widget.WidgetManagerImpl
import org.koin.core.component.inject

class Player(override val name: String, private val session: NetworkSession) : PlayerCharacter {

    private val loginManager: LoginManager<LoginInformation, NetworkSession> by inject()

    override val widgetManager: WidgetManager = WidgetManagerImpl(this)

    override val vsManager: VirtualSystemManager = VirtualSystemManagerImpl(this)

    override fun logout() {
        session.destroy()
        loginManager.logoutQueue.add(this)
    }

    override fun isActive(): Boolean {
        return session.isActive()
    }

    override fun initialize() {
        session.handlePacket(CommandPacket, CommandHandler(this))
        session.handlePacket(WidgetActionPacket, WidgetActionPacketHandler(this))
        session.handlePacket(OpenWidgetPacket, OpenWidgetHandler(this))

        if(vsManager.mountedSystem == null) {
            vsManager.mount("System 0")
        }
    }

    override fun onTick(currentTick: Long) {
        session.sendPacket(SystemInformationPacket(
            name,
            0,
            0,
            0,
            0,
            0,
            0.0,
            vsManager.mountedSystem?.publicAddress ?: "Not Mounted"
        ))
    }
}