package com.hack.game.api.world.device.software

import com.hack.game.api.world.device.processes.VirtualProcess

interface VirtualSoftware {

    val name: String
    val extension: String
    val version: Double
    val size: Int

    fun id() = "${name}-${extension}-${version}"

    var isInstalled: Boolean

    fun install() : VirtualProcess
    fun uninstall() : VirtualProcess

    fun createExecutableProcess(): VirtualProcess

}