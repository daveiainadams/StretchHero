package com.dejvik.stretchhero.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dejvik.stretchhero.data.Routine
import com.dejvik.stretchhero.data.RoutineDataSource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RoutineViewModel : ViewModel() {

    val currentStepIndex: MutableState<Int> = mutableStateOf(0)
    val timeLeftInSeconds: MutableState<Int> = mutableStateOf(0)
    val timerRunning: MutableState<Boolean> = mutableStateOf(false)
    private var timerJob: Job? = null
    val currentRoutine: MutableState<Routine?> = mutableStateOf(null)
    val routineComplete: MutableState<Boolean> = mutableStateOf(false)
    val routineFound: MutableState<Boolean> = mutableStateOf(true)

    fun loadRoutine(routineId: String) {
        timerJob?.cancel()
        timerJob = null
        val routine = RoutineDataSource.getRoutineById(routineId)
        if (routine != null) {
            currentRoutine.value = routine
            routineFound.value = true
            currentStepIndex.value = 0
            routineComplete.value = false
            timerRunning.value = false // Stop timer when loading/reloading
            if (routine.steps.isNotEmpty()) {
                timeLeftInSeconds.value = routine.steps[0].duration
            } else {
                timeLeftInSeconds.value = 0
            }
        } else {
            currentRoutine.value = null
            routineFound.value = false
            currentStepIndex.value = 0
            timeLeftInSeconds.value = 0
            routineComplete.value = false
            timerRunning.value = false
        }
    }

    fun startStepTimer() {
        if (timerRunning.value) return
        if (currentRoutine.value != null && !routineComplete.value) {
            val currentStep = currentRoutine.value?.steps?.getOrNull(currentStepIndex.value)
            if (currentStep != null) {
                if (timeLeftInSeconds.value == 0) {
                    timeLeftInSeconds.value = currentStep.duration
                }
                timerRunning.value = true
                timerJob = viewModelScope.launch {
                    while (timeLeftInSeconds.value > 0 && timerRunning.value) {
                        delay(1000L)
                        if (timerRunning.value) {
                            timeLeftInSeconds.value--
                        }
                    }
                    if (timerRunning.value) {
                        timerRunning.value = false
                        moveToNextStep()
                    }
                }
            }
        }
    }

    fun moveToNextStep() {
        currentRoutine.value?.let { routine ->
            if (currentStepIndex.value < routine.steps.size - 1) {
                currentStepIndex.value++
                val nextStep = routine.steps[currentStepIndex.value]
                timeLeftInSeconds.value = nextStep.duration
                // startStepTimer() // Auto-start next step. This will also trigger TTS via the screen logic observing currentStep
            } else {
                routineComplete.value = true
            }
        }
    }

    fun moveToPreviousStep() {
        currentRoutine.value?.let { routine ->
            if (currentStepIndex.value > 0) {
                currentStepIndex.value--
                val prevStep = routine.steps[currentStepIndex.value]
                timeLeftInSeconds.value = prevStep.duration
            }
        }
    }

    fun restartRoutine() {
        currentRoutine.value?.id?.let {
            loadRoutine(it)
            // Optionally auto-start the first step's timer or wait for user interaction
            // For now, it will just load, and user needs to press play.
        }
    }

    fun stopTimer() {
        timerRunning.value = false
        timerJob?.cancel()
        timerJob = null
    }
}
