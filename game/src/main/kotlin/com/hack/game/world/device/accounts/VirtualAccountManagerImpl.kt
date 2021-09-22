package com.hack.game.world.device.accounts

import com.hack.game.api.vpassword.PasswordGenerator
import com.hack.game.api.world.device.accounts.VirtualAccount
import com.hack.game.api.world.device.accounts.VirtualAccountManager
import com.hack.game.api.world.device.accounts.VirtualAccountPermission
import com.hack.game.world.device.accounts.DefaultVirtualPermissions.isAccountManager
import com.hack.game.world.device.accounts.DefaultVirtualPermissions.isNotHidden
import com.hack.game.world.device.accounts.DefaultVirtualPermissions.hasRoot

class VirtualAccountManagerImpl : VirtualAccountManager {

    private val _accounts = mutableMapOf<String, VirtualAccount>(
        "root" to VirtualAccountImpl("root", PasswordGenerator.newPassword())
    )
    private val _loggedInAccounts = mutableMapOf<String, VirtualAccount>()

    override val accounts: Map<String, VirtualAccount> = _accounts
    override val loggedInAccounts: Map<String, VirtualAccount> = _loggedInAccounts

    override fun createUser(user: VirtualAccount, username: String, password: String) {
        if(_accounts.containsKey(user.username)) {
            if(user.hasRoot() || user.isAccountManager()) {
                val newUser = VirtualAccountImpl(username, password)
                _accounts[newUser.username] = newUser
            }
        }
    }

    override fun deleteUser(user: VirtualAccount, username: String) {
        if((user.hasRoot() || user.isAccountManager()) && _accounts.containsKey(username)) {
            val userToDelete = _accounts[username]!!
            if(userToDelete.isNotHidden()) {
                _accounts.remove(username)
            }
        }
    }

    override fun authoriseFor(user: VirtualAccount, username: String, permission: VirtualAccountPermission) {
        if(user.hasRoot() || user.isAccountManager()) {
            if(_accounts.containsKey(username)) {
                val chmod = _accounts[username]!!
                chmod.permissionManager.approvePermission(permission)
            }
        }
    }

    override fun revokeFor(user: VirtualAccount, username: String, permission: VirtualAccountPermission) {
        if(user.hasRoot() || user.isAccountManager()) {
            if(_accounts.containsKey(username)) {
                val chmod = _accounts[username]!!
                chmod.permissionManager.revokePermission(permission)
            }
        }
    }

    override fun login(username: String, password: String, publicAddress: String): Boolean {
        if(_accounts.containsKey(username)) {
            val acc = _accounts[username]!!
            if(acc.username == username && acc.password == password) {
                _loggedInAccounts[publicAddress] = acc
                return true
            }
        }
        return false
    }

    override fun logout(publicAddress: String) {
        _loggedInAccounts.remove(publicAddress)
    }

    override fun account(publicAddress: String): VirtualAccount {
        return _loggedInAccounts[publicAddress]!!
    }

}