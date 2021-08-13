package com.hack.game.api.world.entity.player.widget

import com.hack.game.api.world.entity.player.PlayerCharacter

interface WidgetActionHandler {

    val widget: ContentTabs

    fun onAction(actionId: String, player: PlayerCharacter)

}