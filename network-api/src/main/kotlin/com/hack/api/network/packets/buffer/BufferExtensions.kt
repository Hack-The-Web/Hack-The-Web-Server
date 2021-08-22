package com.hack.api.network.packets.buffer

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

/*fun ByteBuf.writeString(str: String) {
    writeBytes(str.toByteArray(StandardCharsets.ISO_8859_1))
    writeByte(0)
}*/
object BufferExtensions {
    fun DataOutputStream.writeString(value: String?) {
        if (value != null) {
            write(value.toByteArray())
        }
        writeByte(0)
    }

    fun DataInputStream.readString(): String {
        val sb = StringBuilder()
        while (true) {
            var ch: Int = readUnsignedByte()
            if (ch == 0) {
                break
            }
            if (ch in 128..159) {
                var var7: Char = CHARACTERS[ch - 128]
                if (0 == var7.code) {
                    var7 = '?'
                }
                ch = var7.code
            }
            sb.append(ch.toChar())
        }
        return sb.toString()
    }

    private val CHARACTERS = charArrayOf(
        '\u20ac', '\u0000', '\u201a', '\u0192', '\u201e', '\u2026',
        '\u2020', '\u2021', '\u02c6', '\u2030', '\u0160', '\u2039',
        '\u0152', '\u0000', '\u017d', '\u0000', '\u0000', '\u2018',
        '\u2019', '\u201c', '\u201d', '\u2022', '\u2013', '\u2014',
        '\u02dc', '\u2122', '\u0161', '\u203a', '\u0153', '\u0000',
        '\u017e', '\u0178'
    )

    @JvmStatic
    fun main(args: Array<String>) {
        val bout = ByteArrayOutputStream()
        val buf = DataOutputStream(bout)

        buf.writeString("qwertyuioplkjhgfdsazxcvbnm!@#$%^&*()")

        val bin = ByteArrayInputStream(bout.toByteArray())
        val b = DataInputStream(bin)

        val string = b.readString()

        println(string)

    }
}