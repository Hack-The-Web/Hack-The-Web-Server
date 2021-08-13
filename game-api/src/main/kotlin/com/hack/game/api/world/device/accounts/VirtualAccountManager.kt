package com.hack.game.api.world.device.accounts

import com.hack.game.api.vpassword.PasswordGenerator

interface VirtualAccountManager {

    val accounts: Map<String, VirtualAccount>

    fun createUser(username: String, password: String = PasswordGenerator.newPassword())
    fun deleteUser(user: VirtualAccount, username: String)

    fun authoriseFor(user: VirtualAccount, username: String, permission: VirtualAccountPermission)
    fun revokeFor(user: VirtualAccount, username: String, permission: VirtualAccountPermission)

}