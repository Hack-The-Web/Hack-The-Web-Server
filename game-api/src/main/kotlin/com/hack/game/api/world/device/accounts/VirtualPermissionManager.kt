package com.hack.game.api.world.device.accounts

interface VirtualPermissionManager {

    fun hasPermission(name: String) : Boolean

    fun hasPermission(permission: VirtualAccountPermission) : Boolean {
        return hasPermission(permission.name)
    }

    fun hasPermissions(vararg permission: VirtualAccountPermission) : Boolean {
        for (vap in permission) {
            if(!hasPermission(vap.name)) {
                return false
            }
        }
        return true
    }

    fun approvePermission(permission: VirtualAccountPermission)
    fun revokePermission(permission: VirtualAccountPermission)

}