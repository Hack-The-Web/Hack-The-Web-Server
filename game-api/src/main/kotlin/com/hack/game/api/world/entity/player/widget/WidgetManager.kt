package com.hack.game.api.world.entity.player.widget

interface WidgetManager {

    val openWidget: ContentTabs

    val actionHandlers: Map<ContentTabs, WidgetActionHandler>

    fun open(tab: ContentTabs)

    fun handleAction(actionId: String)

    fun close()

}