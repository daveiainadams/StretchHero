package com.dejvik.stretchhero.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import java.util.concurrent.ConcurrentLinkedQueue

class TextToSpeechHelper(private val context: Context) : TextToSpeech.OnInitListener {
    
    private var tts: TextToSpeech? = null
    private val _isInitialized = MutableStateFlow(false)
    val isInitialized: StateFlow<Boolean> = _isInitialized.asStateFlow()
    
    private val speechQueue = ConcurrentLinkedQueue<String>()
    private var isSpeaking = false

    init {
        initializeTTS()
    }

    private fun initializeTTS() {
        try {
            tts = TextToSpeech(context, this)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize TTS", e)
            _isInitialized.value = false
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            try {
                tts?.let { ttsInstance ->
                    // Set language
                    val result = ttsInstance.setLanguage(Locale.UK)
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.w(TAG, "Language not supported, falling back to default")
                        ttsInstance.setLanguage(Locale.getDefault())
                    }
                    
                    // Set speech rate and pitch
                    ttsInstance.setSpeechRate(0.9f)
                    ttsInstance.setPitch(1.0f)
                    
                    // Set utterance progress listener
                    ttsInstance.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                        override fun onStart(utteranceId: String?) {
                            isSpeaking = true
                            Log.d(TAG, "Started speaking: $utteranceId")
                        }
                        
                        override fun onDone(utteranceId: String?) {
                            isSpeaking = false
                            Log.d(TAG, "Finished speaking: $utteranceId")
                            processNextInQueue()
                        }
                        
                        override fun onError(utteranceId: String?) {
                            isSpeaking = false
                            Log.e(TAG, "Error speaking: $utteranceId")
                            processNextInQueue()
                        }
                    })
                }
                
                _isInitialized.value = true
                Log.i(TAG, "TTS Initialized successfully")
                processNextInQueue()
            } catch (e: Exception) {
                Log.e(TAG, "Error configuring TTS", e)
                _isInitialized.value = false
            }
        } else {
            _isInitialized.value = false
            Log.e(TAG, "TTS Initialization failed. Status: $status")
        }
    }

    fun speak(text: String, priority: Boolean = false) {
        if (text.isBlank()) return
        
        if (priority) {
            // Clear queue and speak immediately
            speechQueue.clear()
            stop()
        }
        
        speechQueue.offer(text)
        
        if (_isInitialized.value && !isSpeaking) {
            processNextInQueue()
        }
    }

    private fun processNextInQueue() {
        if (!_isInitialized.value || isSpeaking) return
        
        val nextText = speechQueue.poll() ?: return
        
        try {
            tts?.speak(nextText, TextToSpeech.QUEUE_FLUSH, null, "utterance_${System.currentTimeMillis()}")
        } catch (e: Exception) {
            Log.e(TAG, "Error speaking text: $nextText", e)
            processNextInQueue() // Try next item in queue
        }
    }

    fun stop() {
        try {
            tts?.stop()
            isSpeaking = false
        } catch (e: Exception) {
            Log.e(TAG, "Error stopping TTS", e)
        }
    }

    fun shutdown() {
        try {
            stop()
            speechQueue.clear()
            tts?.shutdown()
            tts = null
            _isInitialized.value = false
            Log.i(TAG, "TTS shutdown completed")
        } catch (e: Exception) {
            Log.e(TAG, "Error during TTS shutdown", e)
        }
    }

    fun isAvailable(): Boolean {
        return _isInitialized.value && tts != null
    }

    companion object {
        private const val TAG = "TextToSpeechHelper"
    }
}
