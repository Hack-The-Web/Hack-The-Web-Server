package com.hack.game.world.entity

import com.hack.game.api.world.entity.EntityManager
import com.hack.game.api.world.entity.player.PlayerCharacter

class EntityManager : EntityManager {

    override val players: MutableList<PlayerCharacter> = mutableListOf()

}