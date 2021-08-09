package com.hack.game.world.entity.player.commands

import com.hack.api.network.packets.PacketHandler
import com.hack.api.network.packets.incoming.CommandPacket
import com.hack.game.api.world.entity.player.PlayerCharacter
import com.hack.game.api.world.entity.player.command.CommandRepository
import com.hack.game.api.world.entity.player.command.PlayerCommand
import org.koin.core.component.inject

class CommandRepositoryImpl : CommandRepository {

    private val commands = mutableMapOf<String, PlayerCommand>()

    override fun addCommand(name: String, command: PlayerCommand) {
        commands[name] = command
    }

    override fun removeCommand(name: String) {
        commands.remove(name)
    }

    override fun executeCommand(name: String, cmd: String, player: PlayerCharacter) {
        commands[name]?.execute(cmd, player)
    }
}