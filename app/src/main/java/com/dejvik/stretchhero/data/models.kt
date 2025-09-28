package com.dejvik.stretchhero.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable

/**
 * Represents a single stretch step in a routine.
 * @Stable annotation helps Compose optimize recomposition.
 * 
 * @param name The display name of the stretch exercise
 * @param duration Duration in seconds for this step
 * @param imageResIdName Resource name for the step illustration
 * @param description Optional detailed description of how to perform the stretch
 */
@Stable
data class StretchStep(
    val name: String,
    val duration: Int,
    val imageResIdName: String,
    val description: String = ""
) {
    init {
        require(name.isNotBlank()) { "Stretch step name cannot be blank" }
        require(duration > 0) { "Duration must be positive" }
        require(imageResIdName.isNotBlank()) { "Image resource name cannot be blank" }
    }
    
    /**
     * Duration converted to minutes as a float for display purposes.
     */
    val durationInMinutes: Float get() = duration / 60f
    
    /**
     * Human-readable duration string (e.g., "30s" or "1m 30s").
     */
    val formattedDuration: String get() = when {
        duration < 60 -> "${duration}s"
        duration % 60 == 0 -> "${duration / 60}m"
        else -> "${duration / 60}m ${duration % 60}s"
    }
    
    /**
     * Returns true if this is a short stretch (under 30 seconds).
     */
    val isShortStretch: Boolean get() = duration < 30
    
    /**
     * Returns true if this is a long stretch (over 2 minutes).
     */
    val isLongStretch: Boolean get() = duration > 120
    
    /**
     * Returns the difficulty level based on duration.
     */
    val estimatedDifficulty: Difficulty get() = when {
        duration <= 15 -> Difficulty.BEGINNER
        duration <= 45 -> Difficulty.INTERMEDIATE
        else -> Difficulty.ADVANCED
    }
}

/**
 * Represents a complete stretching routine containing multiple steps.
 * @Stable annotation helps Compose optimize recomposition.
 * 
 * @param name The display name of the routine
 * @param steps List of StretchStep objects that make up this routine
 * @param id Unique identifier for the routine
 * @param description Optional description of the routine's purpose and benefits
 * @param difficulty Overall difficulty level of the routine
 */
@Stable
data class Routine(
    val name: String,
    val steps: List<StretchStep>,
    val id: String,
    val description: String = "",
    val difficulty: Difficulty = Difficulty.BEGINNER
) {
    init {
        require(name.isNotBlank()) { "Routine name cannot be blank" }
        require(steps.isNotEmpty()) { "Routine must have at least one step" }
        require(id.isNotBlank()) { "Routine ID cannot be blank" }
    }
    
    /**
     * Total duration of all steps combined in seconds.
     */
    val totalDuration: Int get() = steps.sumOf { it.duration }
    
    /**
     * Total duration converted to minutes as a float for display purposes.
     */
    val totalDurationInMinutes: Float get() = totalDuration / 60f
    
    /**
     * Human-readable total duration string (e.g., "5m 30s").
     */
    val formattedTotalDuration: String get() = when {
        totalDuration < 60 -> "${totalDuration}s"
        totalDuration % 60 == 0 -> "${totalDuration / 60}m"
        else -> "${totalDuration / 60}m ${totalDuration % 60}s"
    }
    
    /**
     * Number of steps in this routine.
     */
    val stepCount: Int get() = steps.size
    
    /**
     * Returns true if this is a quick routine (under 5 minutes).
     */
    val isQuickRoutine: Boolean get() = totalDuration < 300
    
    /**
     * Returns true if this is a long routine (over 15 minutes).
     */
    val isLongRoutine: Boolean get() = totalDuration > 900
    
    /**
     * Gets the estimated calories burned (rough approximation).
     * Based on light stretching activity.
     */
    val estimatedCaloriesBurned: Int get() = (totalDurationInMinutes * 2.5).toInt()
    
    /**
     * Returns a list of unique muscle groups targeted by this routine.
     * This could be enhanced with actual muscle group data in the future.
     */
    val targetedMuscleGroups: List<String> get() {
        // This is a simplified implementation
        // In a real app, each StretchStep would have muscle group data
        return when (difficulty) {
            Difficulty.BEGINNER -> listOf("Neck", "Shoulders", "Back")
            Difficulty.INTERMEDIATE -> listOf("Neck", "Shoulders", "Back", "Hips", "Legs")
            Difficulty.ADVANCED -> listOf("Full Body", "Core", "Deep Muscles")
        }
    }
    
    /**
     * Returns the step at the given index, or null if index is out of bounds.
     * Safer alternative to direct list access.
     */
    fun getStepAt(index: Int): StretchStep? = steps.getOrNull(index)
    
    /**
     * Returns the next step after the given index, or null if at the end.
     */
    fun getNextStep(currentIndex: Int): StretchStep? = getStepAt(currentIndex + 1)
    
    /**
     * Returns the previous step before the given index, or null if at the beginning.
     */
    fun getPreviousStep(currentIndex: Int): StretchStep? = getStepAt(currentIndex - 1)
    
    /**
     * Returns the progress percentage for the given step index.
     */
    fun getProgressPercentage(currentIndex: Int): Float {
        return if (steps.isEmpty()) 0f else (currentIndex.toFloat() / steps.size) * 100f
    }
}

/**
 * Enum representing the difficulty levels for routines and steps.
 */
enum class Difficulty {
    BEGINNER,
    INTERMEDIATE, 
    ADVANCED;
    
    /**
     * Returns a user-friendly display name for the difficulty level.
     */
    val displayName: String get() = when (this) {
        BEGINNER -> "Beginner"
        INTERMEDIATE -> "Intermediate"
        ADVANCED -> "Advanced"
    }
    
    /**
     * Returns a color representation for UI display.
     * This would typically map to theme colors in a real implementation.
     */
    val colorHint: String get() = when (this) {
        BEGINNER -> "#4CAF50"      // Green
        INTERMEDIATE -> "#FF9800"   // Orange
        ADVANCED -> "#F44336"       // Red
    }
    
    /**
     * Returns a brief description of what this difficulty level entails.
     */
    val description: String get() = when (this) {
        BEGINNER -> "Gentle stretches suitable for everyone"
        INTERMEDIATE -> "Moderate stretches requiring some flexibility"
        ADVANCED -> "Challenging stretches for experienced practitioners"
    }
}

/**
 * Extension function to filter routines by difficulty level.
 */
fun List<Routine>.filterByDifficulty(difficulty: Difficulty): List<Routine> {
    return filter { it.difficulty == difficulty }
}

/**
 * Extension function to get routines within a specific duration range.
 */
fun List<Routine>.filterByDuration(minMinutes: Int, maxMinutes: Int): List<Routine> {
    val minSeconds = minMinutes * 60
    val maxSeconds = maxMinutes * 60
    return filter { it.totalDuration in minSeconds..maxSeconds }
}

/**
 * Extension function to sort routines by duration (shortest first).
 */
fun List<Routine>.sortedByDuration(): List<Routine> {
    return sortedBy { it.totalDuration }
}