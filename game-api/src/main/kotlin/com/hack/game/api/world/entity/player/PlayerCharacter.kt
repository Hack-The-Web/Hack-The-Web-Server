package com.hack.game.api.world.entity.player

import com.hack.game.api.world.entity.Character
import com.hack.game.api.world.entity.player.widget.WidgetManager

interface PlayerCharacter : Character {

    val widgetManager: WidgetManager

    fun logout()
    fun isActive() : Boolean
    fun initialize()

}