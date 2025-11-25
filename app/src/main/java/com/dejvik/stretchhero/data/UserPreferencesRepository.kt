package com.dejvik.stretchhero.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferencesRepository(private val context: Context) {

    private object PreferencesKeys {
        val TOTAL_ROUTINES = intPreferencesKey("total_routines")
        val CURRENT_STREAK = intPreferencesKey("current_streak")
        val LAST_WORKOUT_DATE = stringPreferencesKey("last_workout_date")
        val UNLOCKED_ACHIEVEMENTS = stringSetPreferencesKey("unlocked_achievements")
        val COMPLETED_CHALLENGES = stringSetPreferencesKey("completed_challenges")
    }

    val userProgress: Flow<UserProgress> = context.dataStore.data
        .map { preferences ->
            val totalRoutines = preferences[PreferencesKeys.TOTAL_ROUTINES] ?: 0
            val currentStreak = preferences[PreferencesKeys.CURRENT_STREAK] ?: 0
            val lastWorkoutDate = preferences[PreferencesKeys.LAST_WORKOUT_DATE]
            val unlockedAchievements = preferences[PreferencesKeys.UNLOCKED_ACHIEVEMENTS] ?: emptySet()
            val completedChallenges = preferences[PreferencesKeys.COMPLETED_CHALLENGES] ?: emptySet()

            UserProgress(
                totalRoutinesCompleted = totalRoutines,
                currentStreak = currentStreak,
                lastWorkoutDate = lastWorkoutDate,
                unlockedAchievements = unlockedAchievements.toList(),
                completedChallenges = completedChallenges.toList()
            )
        }

    suspend fun updateProgress(progress: UserProgress) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.TOTAL_ROUTINES] = progress.totalRoutinesCompleted
            preferences[PreferencesKeys.CURRENT_STREAK] = progress.currentStreak
            progress.lastWorkoutDate?.let { preferences[PreferencesKeys.LAST_WORKOUT_DATE] = it }
            preferences[PreferencesKeys.UNLOCKED_ACHIEVEMENTS] = progress.unlockedAchievements.toSet()
            preferences[PreferencesKeys.COMPLETED_CHALLENGES] = progress.completedChallenges.toSet()
        }
    }

    suspend fun incrementRoutineCount() {
        context.dataStore.edit { preferences ->
            val currentCount = preferences[PreferencesKeys.TOTAL_ROUTINES] ?: 0
            preferences[PreferencesKeys.TOTAL_ROUTINES] = currentCount + 1
        }
    }
    
    suspend fun updateStreak(newStreak: Int, date: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.CURRENT_STREAK] = newStreak
            preferences[PreferencesKeys.LAST_WORKOUT_DATE] = date
        }
    }
    
    suspend fun unlockAchievement(achievementId: String) {
        context.dataStore.edit { preferences ->
            val current = preferences[PreferencesKeys.UNLOCKED_ACHIEVEMENTS] ?: emptySet()
            preferences[PreferencesKeys.UNLOCKED_ACHIEVEMENTS] = current + achievementId
        }
    }
    
    suspend fun completeChallenge(challengeId: String) {
        context.dataStore.edit { preferences ->
            val current = preferences[PreferencesKeys.COMPLETED_CHALLENGES] ?: emptySet()
            preferences[PreferencesKeys.COMPLETED_CHALLENGES] = current + challengeId
        }
    }
}
