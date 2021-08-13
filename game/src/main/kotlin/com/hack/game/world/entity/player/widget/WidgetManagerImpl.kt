package com.hack.game.world.entity.player.widget

import com.hack.game.api.world.entity.player.PlayerCharacter
import com.hack.game.api.world.entity.player.widget.ContentTabs
import com.hack.game.api.world.entity.player.widget.WidgetActionHandler
import com.hack.game.api.world.entity.player.widget.WidgetManager
import com.hack.game.world.entity.player.widget.actions.GameFrameActionHandler

class WidgetManagerImpl(private val player: PlayerCharacter) : WidgetManager {

    private var _openWidget = ContentTabs.HOME
    private val _actionHandlers = mutableMapOf<ContentTabs, WidgetActionHandler>(
        ContentTabs.GAMEFRAME to GameFrameActionHandler()
    )

    override val openWidget: ContentTabs
        get() = _openWidget
    override val actionHandlers: Map<ContentTabs, WidgetActionHandler>
        get() = _actionHandlers


    override fun open(tab: ContentTabs) {
        if(tab !== openWidget) {
            _openWidget = tab
        }
    }

    override fun handleAction(actionId: String) {
        if(_openWidget === ContentTabs.NONE)
            return
        actionHandlers[openWidget]?.onAction(actionId, player)
    }

    override fun close() {
        _openWidget = ContentTabs.NONE
    }
}