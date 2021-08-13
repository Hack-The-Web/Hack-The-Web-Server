package com.hack.game.world.device.accounts

import com.hack.game.api.world.device.accounts.VirtualAccountPermission
import com.hack.game.api.world.device.accounts.VirtualPermissionManager

class VirtualPermissionManagerImpl : VirtualPermissionManager {

    private val permissions = mutableMapOf<String, VirtualAccountPermission>()

    override fun hasPermission(name: String): Boolean {
        return permissions.containsKey(name)
    }

    override fun approvePermission(permission: VirtualAccountPermission) {
        permissions[permission.name] = permission
    }

    override fun revokePermission(permission: VirtualAccountPermission) {
        permissions[permission.name] = permission
    }
}