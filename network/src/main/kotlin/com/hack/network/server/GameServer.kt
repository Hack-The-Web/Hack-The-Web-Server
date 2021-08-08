package com.hack.network.server

import com.hack.game.api.world.tick.WorldTick
import com.hack.network.server.channel.NetworkChannelInitializer
import com.hack.network.server.channel.handler.NetworkChannelHandler
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GameServer(val port: Int) : KoinComponent {

    private val bossEventGroup: NioEventLoopGroup = NioEventLoopGroup(Runtime.getRuntime().availableProcessors() / 2)
    private val workerEventGroup: NioEventLoopGroup = NioEventLoopGroup(1)

    private val tick: WorldTick by inject()

    fun start() {

        try {
            val bootstrap = ServerBootstrap()
                .group(bossEventGroup, workerEventGroup)
                .channel(NioServerSocketChannel::class.java)
                .childHandler(NetworkChannelInitializer())
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_BACKLOG, 128)
            val future = bootstrap.bind(port).sync()
            future.channel().closeFuture().sync()
        } finally {
            bossEventGroup.shutdownGracefully()
            workerEventGroup.shutdownGracefully()
            tick.shutdownGracefully()
        }

    }

}