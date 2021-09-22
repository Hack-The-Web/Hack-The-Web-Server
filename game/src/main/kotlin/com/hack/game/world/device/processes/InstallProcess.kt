package com.hack.game.world.device.processes

import com.hack.game.api.world.device.processes.VirtualProcess
import com.hack.game.api.world.device.software.VirtualSoftware

class InstallProcess(
    override val threads: Int = 1,
    override val timeInSeconds: Long = 3,
    override val ram: Int,
    override val spawner: VirtualSoftware,
) : VirtualProcess {

    override var isRunning: Boolean = false
    override var elapsedTime: Long = 0L

    override fun onProcessStop() {
        spawner.isInstalled = true
    }
}