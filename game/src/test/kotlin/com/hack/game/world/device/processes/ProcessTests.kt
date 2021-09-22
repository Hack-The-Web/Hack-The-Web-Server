package com.hack.game.world.device.processes

import com.hack.game.world.device.VirtualSystemImpl
import com.hack.game.world.device.software.CrackerSoftware
import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.math.roundToInt

class ProcessTests {

    @Test
    fun `allocate and deallocate processes`() {
        val sys = VirtualSystemImpl("test", "sys")
        val cracker = CrackerSoftware("Basic Cracker")
        val proc = cracker.createExecutableProcess()

        proc.startProcess(sys)

        assert(sys.cpu.usage() == 1) { "Failed to allocate vthreads." }
        assert(sys.cpu.processes()[0] === proc) { "Failed to add process." }

    }

    @Test
    fun `time calc`() {

        var size = 5000
        val threads = 8

        //f(x) = 100*(1+0.2)x

        val rawSeconds = (0.05 * size).roundToInt()
        val seconds = (rawSeconds - (threads * 30))

        val timeInSeconds = if(seconds <= 0) 3 else seconds

        val thrds = rawSeconds / 30
        val requiredThreads = if(thrds <= 0) 1 else thrds

        println("Original Time $rawSeconds")
        println("Seconds $timeInSeconds:$seconds:$requiredThreads")
        println("Minutes ${timeInSeconds / 60}")




    }

}