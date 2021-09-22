package com.hack.game.world.device.events

import com.hack.game.api.world.device.accounts.VirtualAccount
import com.hack.game.api.world.device.events.VirtualSystemEvent
import java.time.LocalDateTime

class VirtualSystemEventImpl(
    override val timestamp: LocalDateTime = LocalDateTime.now(),
    override val account: VirtualAccount,
    override val message: String
) : VirtualSystemEvent