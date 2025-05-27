package com.dejvik.stretchhero.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class TextToSpeechHelper(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var initialized = false

    init {
        tts = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.UK
            initialized = true
            Log.i("TextToSpeechHelper", "TTS Initialized successfully.")
        } else {
            initialized = false
            Log.e("TextToSpeechHelper", "TTS Initialization failed. Status: $status")
        }
    }

    fun speak(text: String) {
        if (initialized && tts != null) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            Log.w("TextToSpeechHelper", "TTS not initialized or null, cannot speak.")
        }
    }

    fun shutdown() {
        if (tts != null) {
            tts?.stop()
            tts?.shutdown()
            initialized = false
            Log.i("TextToSpeechHelper", "TTS shutdown.")
        }
    }
}
