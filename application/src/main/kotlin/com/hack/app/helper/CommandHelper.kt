package com.hack.app.helper

import com.hack.game.api.world.entity.player.command.CommandRepository

object CommandHelper {

    fun addDefaultCommands(repo: CommandRepository) {

        repo.addCommand("test") { args, player ->
            println("Args $args - ${player.name}")
        }

    }

}