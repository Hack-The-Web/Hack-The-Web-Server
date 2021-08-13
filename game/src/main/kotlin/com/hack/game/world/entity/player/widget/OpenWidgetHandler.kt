package com.hack.game.world.entity.player.widget

import com.hack.api.network.packets.PacketHandler
import com.hack.api.network.packets.incoming.OpenWidgetPacket
import com.hack.game.api.world.entity.player.widget.ContentTabs
import com.hack.game.world.entity.player.Player

class OpenWidgetHandler(val player: Player) : PacketHandler<OpenWidgetPacket> {
    override suspend fun handlePacket(value: OpenWidgetPacket) {
        val manager = player.widgetManager
        val tab = ContentTabs.tab(value.tab)
        if(tab != null && manager.openWidget !== tab) {
            manager.close()
            manager.open(tab)
        }
    }
}