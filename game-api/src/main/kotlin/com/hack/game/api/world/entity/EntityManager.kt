package com.hack.game.api.world.entity

import com.hack.game.api.world.entity.player.PlayerCharacter

interface EntityManager {

    val players: MutableList<PlayerCharacter>

}