package com.dejvik.stretchhero.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import java.time.LocalDate

data class UserProgress(
    val totalRoutinesCompleted: Int = 0,
    val currentStreak: Int = 0,
    val lastWorkoutDate: String? = null, // ISO-8601 format: YYYY-MM-DD
    val unlockedAchievements: List<String> = emptyList()
)

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val condition: (UserProgress) -> Boolean
)

object AchievementData {
    val allAchievements = listOf(
        Achievement(
            id = "first_step",
            title = "First Step",
            description = "Complete your first routine",
            icon = Icons.Filled.Star,
            condition = { it.totalRoutinesCompleted >= 1 }
        ),
        Achievement(
            id = "consistency_king",
            title = "Consistency King",
            description = "Reach a 3-day streak",
            icon = Icons.Filled.LocalFireDepartment,
            condition = { it.currentStreak >= 3 }
        ),
        Achievement(
            id = "stretch_master",
            title = "Stretch Master",
            description = "Complete 10 routines",
            icon = Icons.Filled.EmojiEvents,
            condition = { it.totalRoutinesCompleted >= 10 }
        ),
        Achievement(
            id = "dedicated_yogi",
            title = "Dedicated Yogi",
            description = "Reach a 7-day streak",
            icon = Icons.Filled.FitnessCenter,
            condition = { it.currentStreak >= 7 }
        )
    )
}
