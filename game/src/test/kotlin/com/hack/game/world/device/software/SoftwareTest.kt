package com.hack.game.world.device.software

import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.math.roundToInt

class SoftwareTest {

    @Test
    fun `software size calculation`() {

        val version = 50.0

        val size = if(version < 50.0) {
            (9 + (version.pow(3) / 2.5) + (version * 0.05)).roundToInt()
        } else (9 + (version.pow(3) / 2.5) - (version * 0.05)).roundToInt()


        println(size)

    }

}