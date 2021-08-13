package com.hack.game.world.device.accounts

import com.hack.game.api.world.device.accounts.VirtualAccount
import com.hack.game.api.world.device.accounts.VirtualPermissionManager

class VirtualAccountImpl(override val username: String, override val password: String) : VirtualAccount {

    override val permissionManager: VirtualPermissionManager = VirtualPermissionManagerImpl()

}