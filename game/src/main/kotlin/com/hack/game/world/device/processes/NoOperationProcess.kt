package com.hack.game.world.device.processes

import com.hack.game.api.world.device.processes.VirtualProcess
import com.hack.game.api.world.device.software.VirtualSoftware

class NoOperationProcess(
    override val threads: Int = 1,
    override val timeInSeconds: Long = 0,
    override val ram: Int = 0,
    override val spawner: VirtualSoftware
) : VirtualProcess {

    override var elapsedTime: Long = 0L
    override var isRunning: Boolean = false

}