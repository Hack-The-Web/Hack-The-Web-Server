package com.hack.game.api.world.entity.player

import com.hack.game.api.world.entity.Character

interface PlayerCharacter : Character {

    fun logout()
    fun isActive() : Boolean

}