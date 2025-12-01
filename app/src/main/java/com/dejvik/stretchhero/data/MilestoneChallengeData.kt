package com.dejvik.stretchhero.data

data class MilestoneChallenge(
    val id: String,
    val title: String,
    val description: String,
    val requiredRoutines: Int,
    val challengeText: String,
    val successMessage: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Star

object MilestoneChallengeData {
    val allChallenges = listOf(
        MilestoneChallenge(
            id = "touch_toes",
            title = "Trial of Flexibility",
            description = "Complete 3 quests to unlock",
            requiredRoutines = 3,
            challengeText = "Can you touch your toes?",
            successMessage = "Victory! Your flexibility grows stronger!",
            icon = androidx.compose.material.icons.Icons.Filled.EmojiEvents
        ),
        MilestoneChallenge(
            id = "hold_plank",
            title = "Trial of Endurance",
            description = "Complete 7 quests to unlock",
            requiredRoutines = 7,
            challengeText = "Can you hold a plank for 30 seconds?",
            successMessage = "Triumph! Your endurance is legendary!",
            icon = androidx.compose.material.icons.Icons.Filled.FitnessCenter
        ),
        MilestoneChallenge(
            id = "deep_squat",
            title = "Trial of Strength",
            description = "Complete 10 quests to unlock",
            requiredRoutines = 10,
            challengeText = "Can you hold a deep squat for 20 seconds?",
            successMessage = "Champion! Your strength knows no bounds!",
            icon = androidx.compose.material.icons.Icons.Filled.Star
        )
    )
    
    fun getNextChallenge(completedRoutines: Int, completedChallenges: List<String>): MilestoneChallenge? {
        return allChallenges.firstOrNull { challenge ->
            completedRoutines >= challenge.requiredRoutines && 
            !completedChallenges.contains(challenge.id)
        }
    }
}
