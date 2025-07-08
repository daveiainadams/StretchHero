package com.dejvik.stretchhero.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dejvik.stretchhero.data.Routine
import com.dejvik.stretchhero.data.RoutineDataSource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class RoutineUiState(
    val currentRoutine: Routine? = null,
    val currentStepIndex: Int = 0,
    val timeLeftInSeconds: Int = 0,
    val timerRunning: Boolean = false,
    val routineComplete: Boolean = false,
    val routineFound: Boolean = true,
    val isLoading: Boolean = false,
    val error: String? = null
)

class RoutineViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RoutineUiState())
    val uiState: StateFlow<RoutineUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null

    fun loadRoutine(routineId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                timerJob?.cancel()
                timerJob = null
                
                val routine = RoutineDataSource.getRoutineById(routineId)
                if (routine != null) {
                    _uiState.value = RoutineUiState(
                        currentRoutine = routine,
                        currentStepIndex = 0,
                        timeLeftInSeconds = routine.steps.firstOrNull()?.duration ?: 0,
                        timerRunning = false,
                        routineComplete = false,
                        routineFound = true,
                        isLoading = false
                    )
                } else {
                    _uiState.value = RoutineUiState(
                        routineFound = false,
                        error = "Routine not found",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Failed to load routine: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    fun startStepTimer() {
        val currentState = _uiState.value
        if (currentState.timerRunning || currentState.routineComplete) return
        
        val currentStep = currentState.currentRoutine?.steps?.getOrNull(currentState.currentStepIndex)
        if (currentStep != null) {
            val initialTime = if (currentState.timeLeftInSeconds == 0) currentStep.duration else currentState.timeLeftInSeconds
            
            _uiState.value = currentState.copy(
                timeLeftInSeconds = initialTime,
                timerRunning = true
            )
            
            timerJob = viewModelScope.launch {
                while (_uiState.value.timeLeftInSeconds > 0 && _uiState.value.timerRunning) {
                    delay(1000L)
                    if (_uiState.value.timerRunning) {
                        _uiState.value = _uiState.value.copy(
                            timeLeftInSeconds = _uiState.value.timeLeftInSeconds - 1
                        )
                    }
                }
                
                if (_uiState.value.timerRunning) {
                    _uiState.value = _uiState.value.copy(timerRunning = false)
                    moveToNextStep()
                }
            }
        }
    }

    fun moveToNextStep() {
        val currentState = _uiState.value
        currentState.currentRoutine?.let { routine ->
            if (currentState.currentStepIndex < routine.steps.size - 1) {
                val nextIndex = currentState.currentStepIndex + 1
                val nextStep = routine.steps[nextIndex]
                _uiState.value = currentState.copy(
                    currentStepIndex = nextIndex,
                    timeLeftInSeconds = nextStep.duration
                )
            } else {
                _uiState.value = currentState.copy(routineComplete = true)
            }
        }
    }

    fun moveToPreviousStep() {
        val currentState = _uiState.value
        currentState.currentRoutine?.let { routine ->
            if (currentState.currentStepIndex > 0) {
                val prevIndex = currentState.currentStepIndex - 1
                val prevStep = routine.steps[prevIndex]
                _uiState.value = currentState.copy(
                    currentStepIndex = prevIndex,
                    timeLeftInSeconds = prevStep.duration
                )
            }
        }
    }

    fun restartRoutine() {
        val currentState = _uiState.value
        currentState.currentRoutine?.id?.let { routineId ->
            loadRoutine(routineId)
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
        _uiState.value = _uiState.value.copy(timerRunning = false)
    }

    fun pauseTimer() {
        stopTimer()
    }

    fun resumeTimer() {
        startStepTimer()
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
        timerJob = null
    }
}
