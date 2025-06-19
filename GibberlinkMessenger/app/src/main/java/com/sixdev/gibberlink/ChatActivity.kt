package com.sixdev.gibberlink

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ChatActivity : AppCompatActivity() {
    private lateinit var chatView: TextView
    private lateinit var input: EditText
    private lateinit var sendButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        chatView = findViewById(R.id.chatLog)
        input = findViewById(R.id.chatInput)
        sendButton = findViewById(R.id.chatSend)

        val isHost = intent.getBooleanExtra("host", false)
        val ip = intent.getStringExtra("ip") ?: ""

        if (isHost) {
            P2PHost.onMessage = { appendMessage("Partner: $it") }
            P2PHost.start()
        } else {
            P2PClient.connect(ip) { runOnUiThread { appendMessage("Verbunden!") } }
        }

        sendButton.setOnClickListener {
            val msg = input.text.toString()
            val encoded = GibberlinkEncoder.encode(msg)
            P2PClient.send(encoded)
            appendMessage("Du: $msg→ $encoded")
            input.setText("")
        }
    }

    private fun appendMessage(msg: String) {
        runOnUiThread {
            chatView.run {
                append("$msg ")}
        }
    }
}
