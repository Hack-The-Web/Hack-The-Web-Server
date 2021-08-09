package com.hack.game.api.world.entity.player.command

import com.hack.game.api.world.entity.player.PlayerCharacter

fun interface PlayerCommand {

    fun execute(cmd: String, player: PlayerCharacter)

}