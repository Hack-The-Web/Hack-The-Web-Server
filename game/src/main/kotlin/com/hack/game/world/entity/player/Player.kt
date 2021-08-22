package com.hack.game.world.entity.player

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.packets.incoming.CommandPacket
import com.hack.api.network.packets.incoming.OpenWidgetPacket
import com.hack.api.network.packets.incoming.WidgetActionPacket
import com.hack.api.network.packets.outgoing.SystemEventPacket
import com.hack.api.network.packets.outgoing.SystemInformationPacket
import com.hack.api.network.session.NetworkSession
import com.hack.game.api.login.LoginManager
import com.hack.game.api.world.entity.EntityManager
import com.hack.game.api.world.entity.player.PlayerCharacter
import com.hack.game.api.world.entity.player.system.VirtualSystemManager
import com.hack.game.api.world.entity.player.widget.ContentTabs
import com.hack.game.api.world.entity.player.widget.WidgetManager
import com.hack.game.world.device.accounts.root
import com.hack.game.world.device.events.VirtualSystemEventImpl
import com.hack.game.world.entity.player.commands.CommandHandler
import com.hack.game.world.entity.player.system.VirtualSystemManagerImpl
import com.hack.game.world.entity.player.widget.OpenWidgetHandler
import com.hack.game.world.entity.player.widget.WidgetActionPacketHandler
import com.hack.game.world.entity.player.widget.WidgetManagerImpl
import org.koin.core.component.inject
import java.time.LocalDateTime
import java.time.ZoneOffset

class Player(override val name: String, private val session: NetworkSession) : PlayerCharacter {

    private val loginManager: LoginManager<LoginInformation, NetworkSession> by inject()
    private val entityManager: EntityManager by inject()

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
            val sys = vsManager.mountedSystem!!
            sys.systemEvents.fireEvent(VirtualSystemEventImpl(
                LocalDateTime.now(),
                sys.accountManager.root(),
                "Logged in"
            ))
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
            LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
            entityManager.players.size,
            vsManager.mountedSystem?.publicAddress ?: "Not Mounted"
        ))
        if(widgetManager.openWidget === ContentTabs.LOGS && vsManager.mountedSystem != null) {
            val sys = vsManager.mountedSystem!!
            sys.systemEvents.systemEvents.forEachIndexed { index, it ->
                session.sendPacket(SystemEventPacket(it.timestamp, index, it.account.username, it.message))
            }
        } else {
            /*
            If user has Intrusion Detection Software, notify user of system event
            if there IDS version is greater then the attackers Intrusion detection bypass
             */
        }
    }
}