package com.hack.game.api.world.device.accounts

interface VirtualAccount {

    val username: String
    val password: String

    val permissionManager: VirtualPermissionManager

}