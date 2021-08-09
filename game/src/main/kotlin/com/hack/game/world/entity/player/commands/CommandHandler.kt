package com.hack.game.world.entity.player.commands

import com.hack.api.network.packets.PacketHandler
import com.hack.api.network.packets.incoming.CommandPacket
import com.hack.game.api.world.entity.player.PlayerCharacter
import com.hack.game.api.world.entity.player.command.CommandRepository
import org.koin.core.component.inject

class CommandHandler(val player: PlayerCharacter) : PacketHandler<CommandPacket> {

    private val repository: CommandRepository by inject()

    override suspend fun handlePacket(value: CommandPacket) {
        val index = value.command.indexOfFirst { c -> c == ' ' }
        val name = value.command.substring(0, index).trim()
        val args = value.command.substring(index, value.command.length)
        println("Index $index - $name - $args")
        repository.executeCommand(name, args, player)
    }
}