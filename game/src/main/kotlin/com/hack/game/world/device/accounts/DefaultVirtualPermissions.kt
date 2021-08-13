package com.hack.game.world.device.accounts

import com.hack.game.api.world.device.accounts.VirtualAccount

object DefaultVirtualPermissions {

    val ACCOUNT_MANAGER = VirtualPermissionImpl("account_manager")
    val HIDDEN_ACCOUNT = VirtualPermissionImpl("hidden")
    val ROOT = VirtualPermissionImpl("root")
    val WHEEL = VirtualPermissionImpl("wheel")
    val SSH = VirtualPermissionImpl("ssh")
    val FTP = VirtualPermissionImpl("ftp")

    fun VirtualAccount.hasRoot(): Boolean = with(permissionManager) { hasPermission(ROOT) || hasPermission(WHEEL) }
    fun VirtualAccount.isAccountManager(): Boolean = permissionManager.hasPermission(ACCOUNT_MANAGER)
    fun VirtualAccount.isHidden(): Boolean = permissionManager.hasPermission(HIDDEN_ACCOUNT)
    fun VirtualAccount.isNotHidden(): Boolean = !isHidden()
    fun VirtualAccount.hasFTP(): Boolean = permissionManager.hasPermission(FTP)
    fun VirtualAccount.hasSSH(): Boolean = permissionManager.hasPermission(SSH)
    fun VirtualAccount.hasRX(): Boolean = permissionManager.hasPermissions(FTP, SSH)

}