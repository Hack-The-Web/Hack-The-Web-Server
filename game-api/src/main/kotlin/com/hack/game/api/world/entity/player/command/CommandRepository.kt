package com.hack.game.api.world.entity.player.command

import com.hack.game.api.world.entity.player.PlayerCharacter

interface CommandRepository {

    fun addCommand(name: String, command: PlayerCommand)
    fun removeCommand(name: String)

    fun executeCommand(name: String, cmd: String, player: PlayerCharacter)

}