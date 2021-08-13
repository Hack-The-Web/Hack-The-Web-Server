package com.hack.game.api.vpassword

import java.security.SecureRandom
import kotlin.random.Random


object PasswordGenerator {

    fun newPassword(len: Int = Random.nextInt(30)): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val random = SecureRandom()
        val sb = StringBuilder()
        for (i in 0 until len) {
            val randomIndex = random.nextInt(chars.length)
            sb.append(chars[randomIndex])
        }
        return sb.toString()
    }

}