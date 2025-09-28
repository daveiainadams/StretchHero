package com.dejvik.stretchhero.data

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Unit tests for data models (StretchStep, Routine, Difficulty).
 * Tests validation, computed properties, and business logic.
 */
class ModelsTest {

    @Test
    fun `StretchStep should calculate duration correctly`() {
        val step = StretchStep(
            name = "Test Stretch",
            duration = 90,
            imageResIdName = "test_image"
        )
        
        assertEquals(1.5f, step.durationInMinutes)
        assertEquals("1m 30s", step.formattedDuration)
    }

    @Test
    fun `StretchStep should format short duration correctly`() {
        val step = StretchStep(
            name = "Test Stretch",
            duration = 30,
            imageResIdName = "test_image"
        )
        
        assertEquals("30s", step.formattedDuration)
        assertTrue(step.isShortStretch)
        assertFalse(step.isLongStretch)
    }

    @Test
    fun `StretchStep should format exact minute duration correctly`() {
        val step = StretchStep(
            name = "Test Stretch",
            duration = 120, // Exactly 2 minutes
            imageResIdName = "test_image"
        )
        
        assertEquals("2m", step.formattedDuration)
        assertFalse(step.isShortStretch)
        assertFalse(step.isLongStretch)
    }

    @Test
    fun `StretchStep should identify long stretches correctly`() {
        val step = StretchStep(
            name = "Deep Stretch",
            duration = 180, // 3 minutes
            imageResIdName = "test_image"
        )
        
        assertTrue(step.isLongStretch)
        assertFalse(step.isShortStretch)
        assertEquals(Difficulty.ADVANCED, step.estimatedDifficulty)
    }

    @Test
    fun `StretchStep should validate name is not blank`() {
        assertFailsWith<IllegalArgumentException> {
            StretchStep(
                name = "",
                duration = 30,
                imageResIdName = "test_image"
            )
        }
        
        assertFailsWith<IllegalArgumentException> {
            StretchStep(
                name = "   ", // Whitespace only
                duration = 30,
                imageResIdName = "test_image"
            )
        }
    }

    @Test
    fun `StretchStep should validate duration is positive`() {
        assertFailsWith<IllegalArgumentException> {
            StretchStep(
                name = "Test",
                duration = 0,
                imageResIdName = "test_image"
            )
        }
        
        assertFailsWith<IllegalArgumentException> {
            StretchStep(
                name = "Test",
                duration = -10,
                imageResIdName = "test_image"
            )
        }
    }

    @Test
    fun `StretchStep should validate imageResIdName is not blank`() {
        assertFailsWith<IllegalArgumentException> {
            StretchStep(
                name = "Test",
                duration = 30,
                imageResIdName = ""
            )
        }
    }

    @Test
    fun `StretchStep should determine difficulty correctly`() {
        val beginnerStep = StretchStep("Easy", 10, "image")
        val intermediateStep = StretchStep("Medium", 30, "image")
        val advancedStep = StretchStep("Hard", 60, "image")
        
        assertEquals(Difficulty.BEGINNER, beginnerStep.estimatedDifficulty)
        assertEquals(Difficulty.INTERMEDIATE, intermediateStep.estimatedDifficulty)
        assertEquals(Difficulty.ADVANCED, advancedStep.estimatedDifficulty)
    }

    @Test
    fun `Routine should calculate total duration correctly`() {
        val routine = Routine(
            id = "test",
            name = "Test Routine",
            steps = listOf(
                StretchStep("Step 1", 30, "image1"),
                StretchStep("Step 2", 45, "image2"),
                StretchStep("Step 3", 60, "image3")
            )
        )
        
        assertEquals(135, routine.totalDuration) // 30 + 45 + 60
        assertEquals(2.25f, routine.totalDurationInMinutes)
        assertEquals("2m 15s", routine.formattedTotalDuration)
        assertEquals(3, routine.stepCount)
    }

    @Test
    fun `Routine should format total duration for exact minutes`() {
        val routine = Routine(
            id = "test",
            name = "Test Routine",
            steps = listOf(
                StretchStep("Step 1", 60, "image1"), // 1 minute
                StretchStep("Step 2", 120, "image2"), // 2 minutes
                StretchStep("Step 3", 60, "image3")  // 1 minute
            )
        )
        
        assertEquals(240, routine.totalDuration) // 4 minutes exactly
        assertEquals("4m", routine.formattedTotalDuration)
    }

    @Test
    fun `Routine should identify quick and long routines`() {
        val quickRoutine = Routine(
            id = "quick",
            name = "Quick Stretch",
            steps = listOf(StretchStep("Quick", 120, "image")) // 2 minutes
        )
        
        val longRoutine = Routine(
            id = "long",
            name = "Long Stretch",
            steps = listOf(StretchStep("Long", 1200, "image")) // 20 minutes
        )
        
        assertTrue(quickRoutine.isQuickRoutine)
        assertFalse(quickRoutine.isLongRoutine)
        
        assertFalse(longRoutine.isQuickRoutine)
        assertTrue(longRoutine.isLongRoutine)
    }

    @Test
    fun `Routine should calculate estimated calories burned`() {
        val routine = Routine(
            id = "test",
            name = "Test Routine",
            steps = listOf(StretchStep("Step", 600, "image")) // 10 minutes
        )
        
        val expectedCalories = (10f * 2.5f).toInt() // 25 calories
        assertEquals(expectedCalories, routine.estimatedCaloriesBurned)
    }

    @Test
    fun `Routine should provide targeted muscle groups based on difficulty`() {
        val beginnerRoutine = Routine(
            id = "beginner",
            name = "Beginner",
            steps = listOf(StretchStep("Step", 30, "image")),
            difficulty = Difficulty.BEGINNER
        )
        
        val advancedRoutine = Routine(
            id = "advanced", 
            name = "Advanced",
            steps = listOf(StretchStep("Step", 30, "image")),
            difficulty = Difficulty.ADVANCED
        )
        
        assertEquals(listOf("Neck", "Shoulders", "Back"), beginnerRoutine.targetedMuscleGroups)
        assertEquals(listOf("Full Body", "Core", "Deep Muscles"), advancedRoutine.targetedMuscleGroups)
    }

    @Test
    fun `Routine should validate has steps`() {
        assertFailsWith<IllegalArgumentException> {
            Routine(
                id = "test",
                name = "Test Routine",
                steps = emptyList()
            )
        }
    }

    @Test
    fun `Routine should validate name is not blank`() {
        assertFailsWith<IllegalArgumentException> {
            Routine(
                id = "test",
                name = "",
                steps = listOf(StretchStep("Step", 30, "image"))
            )
        }
    }

    @Test
    fun `Routine should validate ID is not blank`() {
        assertFailsWith<IllegalArgumentException> {
            Routine(
                id = "",
                name = "Test",
                steps = listOf(StretchStep("Step", 30, "image"))
            )
        }
    }

    @Test
    fun `Routine should safely get steps by index`() {
        val routine = Routine(
            id = "test",
            name = "Test",
            steps = listOf(
                StretchStep("Step 1", 30, "image1"),
                StretchStep("Step 2", 45, "image2")
            )
        )
        
        assertEquals("Step 1", routine.getStepAt(0)?.name)
        assertEquals("Step 2", routine.getStepAt(1)?.name)
        assertEquals(null, routine.getStepAt(2)) // Out of bounds
        assertEquals(null, routine.getStepAt(-1)) // Negative index
    }

    @Test
    fun `Routine should get next and previous steps correctly`() {
        val routine = Routine(
            id = "test",
            name = "Test",
            steps = listOf(
                StretchStep("Step 1", 30, "image1"),
                StretchStep("Step 2", 45, "image2"),
                StretchStep("Step 3", 60, "image3")
            )
        )
        
        assertEquals("Step 2", routine.getNextStep(0)?.name)
        assertEquals("Step 3", routine.getNextStep(1)?.name)
        assertEquals(null, routine.getNextStep(2)) // At end
        
        assertEquals(null, routine.getPreviousStep(0)) // At beginning
        assertEquals("Step 1", routine.getPreviousStep(1)?.name)
        assertEquals("Step 2", routine.getPreviousStep(2)?.name)
    }

    @Test
    fun `Routine should calculate progress percentage correctly`() {
        val routine = Routine(
            id = "test",
            name = "Test",
            steps = listOf(
                StretchStep("Step 1", 30, "image1"),
                StretchStep("Step 2", 45, "image2"),
                StretchStep("Step 3", 60, "image3"),
                StretchStep("Step 4", 30, "image4")
            )
        )
        
        assertEquals(0f, routine.getProgressPercentage(0)) // 0/4 = 0%
        assertEquals(25f, routine.getProgressPercentage(1)) // 1/4 = 25%
        assertEquals(50f, routine.getProgressPercentage(2)) // 2/4 = 50%
        assertEquals(75f, routine.getProgressPercentage(3)) // 3/4 = 75%
    }

    @Test
    fun `Difficulty enum should have correct display properties`() {
        assertEquals("Beginner", Difficulty.BEGINNER.displayName)
        assertEquals("Intermediate", Difficulty.INTERMEDIATE.displayName)
        assertEquals("Advanced", Difficulty.ADVANCED.displayName)
        
        assertEquals("#4CAF50", Difficulty.BEGINNER.colorHint)
        assertEquals("#FF9800", Difficulty.INTERMEDIATE.colorHint)
        assertEquals("#F44336", Difficulty.ADVANCED.colorHint)
        
        assertTrue(Difficulty.BEGINNER.description.contains("Gentle"))
        assertTrue(Difficulty.INTERMEDIATE.description.contains("Moderate"))
        assertTrue(Difficulty.ADVANCED.description.contains("Challenging"))
    }

    @Test
    fun `Extension functions should work correctly`() {
        val routines = listOf(
            Routine("1", "Beginner Routine", listOf(StretchStep("S1", 60, "i1")), difficulty = Difficulty.BEGINNER),
            Routine("2", "Advanced Routine", listOf(StretchStep("S2", 300, "i2")), difficulty = Difficulty.ADVANCED),
            Routine("3", "Quick Routine", listOf(StretchStep("S3", 120, "i3")), difficulty = Difficulty.INTERMEDIATE)
        )
        
        // Test filtering by difficulty
        val beginnerRoutines = routines.filterByDifficulty(Difficulty.BEGINNER)
        assertEquals(1, beginnerRoutines.size)
        assertEquals("Beginner Routine", beginnerRoutines.first().name)
        
        // Test filtering by duration (2-6 minutes)
        val mediumDurationRoutines = routines.filterByDuration(2, 6)
        assertEquals(2, mediumDurationRoutines.size) // 60s and 300s routines
        
        // Test sorting by duration
        val sortedRoutines = routines.sortedByDuration()
        assertEquals("Beginner Routine", sortedRoutines.first().name) // Shortest (60s)
        assertEquals("Advanced Routine", sortedRoutines.last().name) // Longest (300s)
    }
}