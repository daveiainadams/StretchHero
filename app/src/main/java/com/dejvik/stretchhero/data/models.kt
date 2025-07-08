package com.dejvik.stretchhero.data

import androidx.annotation.DrawableRes

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
    
    val durationInMinutes: Float get() = duration / 60f
    val formattedDuration: String get() = when {
        duration < 60 -> "${duration}s"
        else -> "${duration / 60}m ${duration % 60}s"
    }
}

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
    
    val totalDuration: Int get() = steps.sumOf { it.duration }
    val totalDurationInMinutes: Float get() = totalDuration / 60f
    val formattedTotalDuration: String get() = when {
        totalDuration < 60 -> "${totalDuration}s"
        else -> "${totalDuration / 60}m ${totalDuration % 60}s"
    }
    val stepCount: Int get() = steps.size
}

enum class Difficulty {
    BEGINNER, INTERMEDIATE, ADVANCED
}
