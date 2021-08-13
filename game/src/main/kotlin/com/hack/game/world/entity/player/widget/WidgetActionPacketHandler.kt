package com.hack.game.world.entity.player.widget

import com.hack.api.network.packets.PacketHandler
import com.hack.api.network.packets.incoming.WidgetActionPacket
import com.hack.game.api.world.entity.player.widget.ContentTabs
import com.hack.game.world.entity.player.Player

class WidgetActionPacketHandler(val player: Player) : PacketHandler<WidgetActionPacket> {
    override suspend fun handlePacket(value: WidgetActionPacket) {
        val widgetManager = player.widgetManager
        val tab = ContentTabs.tab(value.tab)
        if(tab != null && widgetManager.openWidget === tab) {
            widgetManager.handleAction(value.actionId)
        }
    }
}