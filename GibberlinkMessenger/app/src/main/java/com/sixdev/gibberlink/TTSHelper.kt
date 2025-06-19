package com.sixdev.gibberlink

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

object TTSHelper {
    private var tts: TextToSpeech? = null

    fun init(context: Context) {
        tts = TextToSpeech(context) {
            if (it == TextToSpeech.SUCCESS) {
                tts?.language = Locale.US
            }
        }
    }

    fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}
