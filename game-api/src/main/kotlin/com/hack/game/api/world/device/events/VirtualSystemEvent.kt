package com.hack.game.api.world.device.events

import com.hack.game.api.world.device.accounts.VirtualAccount
import java.time.LocalDateTime

interface VirtualSystemEvent {

    val timestamp: LocalDateTime
    val message: String
    val account: VirtualAccount

}