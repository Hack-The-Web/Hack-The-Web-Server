package com.hack.network.server.channel.login

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.login.LoginResponse
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import java.nio.charset.Charset

class LoginDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        val usernameLength = buf.readShort().toInt()
        val passwordLength = buf.readShort().toInt()
        val username = buf.readCharSequence(usernameLength, Charset.defaultCharset()).toString()
        val password = buf.readCharSequence(passwordLength, Charset.defaultCharset()).toString()
        println(username)
        println(password)
        out.add(LoginResponse(1, LoginInformation(username, password)))
    }
}