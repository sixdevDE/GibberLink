package com.sixdev.gibberlink

import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread

object P2PHost {
    private var serverSocket: ServerSocket? = null
    var onMessage: ((String) -> Unit)? = null

    fun start(port: Int = 5454) {
        thread {
            serverSocket = ServerSocket(port)
            val client = serverSocket!!.accept()
            val reader = client.getInputStream().bufferedReader()
            while (true) {
                val msg = reader.readLine() ?: break
                onMessage?.invoke(msg)
            }
        }
    }

    fun stop() {
        serverSocket?.close()
    }
}
