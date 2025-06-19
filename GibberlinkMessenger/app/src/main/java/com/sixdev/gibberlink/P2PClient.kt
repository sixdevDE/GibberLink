package com.sixdev.gibberlink

import java.io.PrintWriter
import java.net.Socket
import kotlin.concurrent.thread

object P2PClient {
    private var writer: PrintWriter? = null

    fun connect(ip: String, port: Int = 5454, onConnected: () -> Unit) {
        thread {
            val socket = Socket(ip, port)
            writer = PrintWriter(socket.getOutputStream(), true)
            onConnected()
        }
    }

    fun send(msg: String) {
        writer?.println(msg)
    }
}
