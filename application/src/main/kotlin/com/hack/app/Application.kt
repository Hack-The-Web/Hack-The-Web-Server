package com.hack.app

import com.hack.api.network.login.LoginInformation
import com.hack.api.network.session.NetworkSession
import com.hack.game.api.login.LoginManager
import com.hack.game.api.world.entity.EntityManager
import com.hack.game.api.world.tick.WorldTick
import com.hack.game.world.tick.events.LoginEvent
import com.hack.game.world.tick.events.PlayersEvent
import com.hack.network.server.GameServer
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

object Application {

    @JvmStatic
    fun main(args: Array<String>) {

        startKoin {
            modules(module {
                single<LoginManager<LoginInformation, NetworkSession>> { com.hack.game.login.LoginManager() }
                single<EntityManager> { com.hack.game.world.entity.EntityManager() }
                single<WorldTick> { com.hack.game.world.tick.WorldTick() }
            })
        }
        val server = GameServer(50000)

        val worldTick = GlobalContext.get().get<WorldTick>()

        worldTick.subscribeEvent(PlayersEvent())
        worldTick.subscribeEvent(LoginEvent())

        server.start()

    }

}