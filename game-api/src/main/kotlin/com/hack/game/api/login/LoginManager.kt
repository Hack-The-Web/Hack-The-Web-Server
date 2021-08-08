package com.hack.game.api.login

import com.hack.game.api.world.entity.player.PlayerCharacter

interface LoginManager<T, S> {

    val loginQueue: ArrayDeque<PlayerCharacter>
    val logoutQueue: ArrayDeque<PlayerCharacter>

    fun initializeLogin(info: T, session: S)

    fun validate(player: PlayerCharacter) : Boolean

}