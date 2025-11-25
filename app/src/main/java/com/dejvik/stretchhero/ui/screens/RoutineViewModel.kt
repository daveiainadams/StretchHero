package com.dejvik.stretchhero.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dejvik.stretchhero.data.AchievementData
import com.dejvik.stretchhero.data.Routine
import com.dejvik.stretchhero.data.RoutineDataSource
import com.dejvik.stretchhero.data.RoutineError
import com.dejvik.stretchhero.data.UserPreferencesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

data class RoutineUiState(
    val currentRoutine: Routine? = null,
    val currentStepIndex: Int = 0,
    val timeLeftInSeconds: Int = 0,
    val timerRunning: Boolean = false,
    val routineComplete: Boolean = false,
    val routineFound: Boolean = true,
    val isLoading: Boolean = false,
    val error: RoutineError? = null,
    val newlyUnlockedAchievements: List<com.dejvik.stretchhero.data.Achievement> = emptyList()
)

class RoutineViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(RoutineUiState())
    val uiState: StateFlow<RoutineUiState> = _uiState.asStateFlow()

    private val repository = UserPreferencesRepository(application)
    private var timerJob: Job? = null

    init {
        viewModelScope.launch {
            repository.userProgress.collect { progress ->
                // You could expose progress in UI state if needed, 
                // but for now we just need it for logic updates
            }
        }
    }

    fun loadRoutine(routineId: String) {
        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            timerJob?.cancel()
            timerJob = null
            // Simulate network delay if needed, but for local data it's fast
            delay(500) 
            val routine = RoutineDataSource.getRoutineById(routineId)
            if (routine != null) {
                _uiState.update { 
                    it.copy(
                        currentRoutine = routine, 
                        currentStepIndex = 0,
                        timeLeftInSeconds = routine.steps.firstOrNull()?.duration ?: 0,
                        routineFound = true,
                        isLoading = false,
                        routineComplete = false,
                        timerRunning = false
                    ) 
                }
            } else {
                _uiState.update { 
                    it.copy(
                        routineFound = false, 
                        error = RoutineError.RoutineNotFound,
                        isLoading = false
                    ) 
                }
            }
        }
    }

    fun startStepTimer() {
        val currentState = _uiState.value
        if (currentState.timerRunning || currentState.routineComplete) return

        val currentStep = currentState.currentRoutine?.steps?.getOrNull(currentState.currentStepIndex)
        if (currentStep != null) {
            val initialTime = if (currentState.timeLeftInSeconds == 0) currentStep.duration else currentState.timeLeftInSeconds

            _uiState.update { it.copy(timeLeftInSeconds = initialTime, timerRunning = true) }
            
            timerJob = viewModelScope.launch {
                while (_uiState.value.timeLeftInSeconds > 0 && _uiState.value.timerRunning) {
                    delay(1000)
                    if (_uiState.value.timerRunning) {
                        _uiState.update { it.copy(timeLeftInSeconds = it.timeLeftInSeconds - 1) }
                    }
                }
                // Timer finished naturally
                if (_uiState.value.timerRunning) {
                    _uiState.update { it.copy(timerRunning = false) }
                    moveToNextStep()
                }
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
        _uiState.update { it.copy(timerRunning = false) }
    }

    fun pauseTimer() {
        stopTimer()
    }

    fun resumeTimer() {
        startStepTimer()
    }

    fun moveToNextStep() {
        val currentState = _uiState.value
        val currentRoutine = currentState.currentRoutine ?: return
        
        if (currentState.currentStepIndex < currentRoutine.steps.size - 1) {
            val nextIndex = currentState.currentStepIndex + 1
            val nextStep = currentRoutine.steps[nextIndex]
            _uiState.update { 
                it.copy(
                    currentStepIndex = nextIndex,
                    timeLeftInSeconds = nextStep.duration,
                    timerRunning = false
                ) 
            }
            // Optional: Auto-start next step
            // startStepTimer()
        } else {
            // Routine Complete
            finishRoutine()
        }
    }

    fun moveToPreviousStep() {
        val currentState = _uiState.value
        val currentRoutine = currentState.currentRoutine ?: return

        if (currentState.currentStepIndex > 0) {
            val prevIndex = currentState.currentStepIndex - 1
            val prevStep = currentRoutine.steps[prevIndex]
            stopTimer()
            _uiState.update { 
                it.copy(
                    currentStepIndex = prevIndex,
                    timeLeftInSeconds = prevStep.duration,
                    timerRunning = false
                ) 
            }
        }
    }

    private fun finishRoutine() {
        stopTimer()
        _uiState.update { it.copy(routineComplete = true) }
        updateUserProgress()
    }

    private fun updateUserProgress() {
        viewModelScope.launch {
            val currentProgress = repository.userProgress.first()
            val today = LocalDate.now().toString()
            
            // Update Streak
            val lastDate = currentProgress.lastWorkoutDate
            val newStreak = if (lastDate == LocalDate.now().minusDays(1).toString()) {
                currentProgress.currentStreak + 1
            } else if (lastDate == today) {
                currentProgress.currentStreak // Already worked out today
            } else {
                1 // Reset streak
            }

            // Update stats
            val updatedProgress = currentProgress.copy(
                totalRoutinesCompleted = currentProgress.totalRoutinesCompleted + 1,
                currentStreak = newStreak,
                lastWorkoutDate = today
            )
            repository.updateProgress(updatedProgress)

            // Check Achievements and track newly unlocked ones
            val newlyUnlocked = mutableListOf<com.dejvik.stretchhero.data.Achievement>()
            AchievementData.allAchievements.forEach { achievement ->
                if (!updatedProgress.unlockedAchievements.contains(achievement.id)) {
                    if (achievement.condition(updatedProgress)) {
                        repository.unlockAchievement(achievement.id)
                        newlyUnlocked.add(achievement)
                    }
                }
            }
            
            // Update UI state with newly unlocked achievements
            _uiState.update { it.copy(newlyUnlockedAchievements = newlyUnlocked) }
        }
    }

    fun restartRoutine() {
        val currentState = _uiState.value
        currentState.currentRoutine?.id?.let { routineId ->
            loadRoutine(routineId)
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
    
    suspend fun getUserProgress() = repository.userProgress.first()
    
    suspend fun completeChallenge(challengeId: String) {
        repository.completeChallenge(challengeId)
    }
    
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
