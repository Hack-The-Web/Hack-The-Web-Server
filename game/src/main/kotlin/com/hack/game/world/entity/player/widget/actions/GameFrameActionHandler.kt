package com.hack.game.world.entity.player.widget.actions

import com.hack.game.api.world.entity.player.PlayerCharacter
import com.hack.game.api.world.entity.player.widget.ContentTabs
import com.hack.game.api.world.entity.player.widget.WidgetActionHandler

class GameFrameActionHandler : WidgetActionHandler {

    override val widget: ContentTabs = ContentTabs.GAMEFRAME

    override fun onAction(actionId: String, player: PlayerCharacter) {

        when(actionId) {
            "logout" -> player.logout()
        }
        
    }

}