package com.dejvik.stretchhero.ui.screens

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.dejvik.stretchhero.data.Difficulty
import com.dejvik.stretchhero.data.Routine
import com.dejvik.stretchhero.data.RoutineError
import com.dejvik.stretchhero.data.StretchStep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Comprehensive unit tests for RoutineViewModel.
 * Tests state management, timer functionality, navigation, and error handling.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class RoutineViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: RoutineViewModel

    private val testRoutine = Routine(
        id = "test-routine",
        name = "Test Routine",
        description = "A test routine for unit testing",
        difficulty = Difficulty.BEGINNER,
        steps = listOf(
            StretchStep(
                name = "Test Stretch 1",
                duration = 30,
                imageResIdName = "test_image_1",
                description = "First test stretch"
            ),
            StretchStep(
                name = "Test Stretch 2",
                duration = 45,
                imageResIdName = "test_image_2",
                description = "Second test stretch"
            ),
            StretchStep(
                name = "Test Stretch 3",
                duration = 60,
                imageResIdName = "test_image_3",
                description = "Third test stretch"
            )
        )
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = RoutineViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should have correct default values`() = runTest {
        viewModel.uiState.test {
            val initialState = awaitItem()
            
            assertNull(initialState.currentRoutine)
            assertEquals(0, initialState.currentStepIndex)
            assertEquals(0, initialState.timeLeftInSeconds)
            assertFalse(initialState.timerRunning)
            assertFalse(initialState.routineComplete)
            assertTrue(initialState.routineFound)
            assertFalse(initialState.isLoading)
            assertNull(initialState.error)
        }
    }

    @Test
    fun `loadRoutine with invalid ID should set error state`() = runTest {
        viewModel.loadRoutine("invalid-routine-id")

        viewModel.uiState.test {
            val state = awaitItem()

            assertFalse(state.routineFound)
            assertTrue("Expected RoutineNotFound error", state.error is RoutineError.RoutineNotFound)
            assertFalse(state.isLoading)
            assertNull(state.currentRoutine)
        }
    }

    @Test
    fun `stopTimer should cancel timer and update state`() = runTest {
        viewModel.stopTimer()
        
        viewModel.uiState.test {
            val state = awaitItem()
            assertFalse(state.timerRunning)
        }
    }

    @Test
    fun `clearError should remove error from state`() = runTest {
        // First trigger an error
        viewModel.loadRoutine("invalid-id")

        // Then clear it
        viewModel.clearError()

        viewModel.uiState.test {
            val state = awaitItem()
            assertNull(state.error)
        }
    }

    @Test
    fun `loadRoutine with valid ID should initialize state correctly`() = runTest {
        viewModel.loadRoutine("quick-desk-stretch")

        viewModel.uiState.test {
            val state = awaitItem()

            assertTrue("Routine should be found", state.routineFound)
            assertEquals("Expected routine to be loaded", "Quick Desk Stretch", state.currentRoutine?.name)
            assertEquals("Should start at first step", 0, state.currentStepIndex)
            assertEquals("Time should match first step duration", testRoutine.steps.first().duration, state.timeLeftInSeconds)
            assertFalse("Timer should not be running initially", state.timerRunning)
            assertFalse("Routine should not be complete initially", state.routineComplete)
            assertNull("Should have no errors", state.error)
        }
    }

    @Test
    fun `startStepTimer should start timer and set state correctly`() = runTest {
        viewModel.loadRoutine("quick-desk-stretch")
        viewModel.startStepTimer()

        viewModel.uiState.test {
            val state = awaitItem()

            assertTrue("Timer should be running", state.timerRunning)
            assertTrue("Time should be greater than 0", state.timeLeftInSeconds > 0)
        }
    }

    @Test
    fun `pauseTimer should stop timer without losing progress`() = runTest {
        viewModel.loadRoutine("quick-desk-stretch")
        viewModel.startStepTimer()

        // Let some time pass
        testScheduler.advanceTimeBy(2000)

        viewModel.pauseTimer()

        viewModel.uiState.test {
            val state = awaitItem()

            assertFalse("Timer should not be running after pause", state.timerRunning)
            assertTrue("Time remaining should be less than initial", state.timeLeftInSeconds < testRoutine.steps.first().duration)
        }
    }

    @Test
    fun `resumeTimer should restart timer from where it was paused`() = runTest {
        viewModel.loadRoutine("quick-desk-stretch")
        viewModel.startStepTimer()

        // Pause and resume
        viewModel.pauseTimer()
        val timeBeforeResume = viewModel.uiState.value.timeLeftInSeconds
        viewModel.resumeTimer()

        viewModel.uiState.test {
            val state = awaitItem()

            assertTrue("Timer should be running after resume", state.timerRunning)
            assertEquals("Time should remain the same", timeBeforeResume, state.timeLeftInSeconds)
        }
    }

    @Test
    fun `moveToNextStep should stop timer and advance to next step`() = runTest {
        viewModel.loadRoutine("quick-desk-stretch")
        viewModel.startStepTimer()

        viewModel.moveToNextStep()

        viewModel.uiState.test {
            val state = awaitItem()

            assertEquals("Should be on second step", 1, state.currentStepIndex)
            assertFalse("Timer should be stopped", state.timerRunning)
        }
    }

    @Test
    fun `moveToPreviousStep should stop timer and go to previous step`() = runTest {
        viewModel.loadRoutine("quick-desk-stretch")
        viewModel.moveToNextStep()  // Move to step 1
        viewModel.moveToNextStep()  // Move to step 2

        viewModel.moveToPreviousStep()  // Go back to step 1

        viewModel.uiState.test {
            val state = awaitItem()

            assertEquals("Should be on second step (index 1)", 1, state.currentStepIndex)
            assertFalse("Timer should be stopped", state.timerRunning)
        }
    }

    @Test
    fun `moveToNextStep at last step should mark routine as complete`() = runTest {
        viewModel.loadRoutine("quick-desk-stretch")

        // Move through all steps
        val routine = viewModel.uiState.value.currentRoutine!!
        repeat(routine.steps.size) {
            viewModel.moveToNextStep()
        }

        viewModel.uiState.test {
            val state = awaitItem()

            assertTrue("Routine should be complete", state.routineComplete)
            assertFalse("Timer should be stopped", state.timerRunning)
        }
    }

    @Test
    fun `moveToPreviousStep at first step should not change index`() = runTest {
        viewModel.loadRoutine("quick-desk-stretch")

        viewModel.moveToPreviousStep()

        viewModel.uiState.test {
            val state = awaitItem()

            assertEquals("Should remain at first step", 0, state.currentStepIndex)
        }
    }

    @Test
    fun `restartRoutine should reset to initial state`() = runTest {
        viewModel.loadRoutine("quick-desk-stretch")
        viewModel.moveToNextStep()
        viewModel.moveToNextStep()

        viewModel.restartRoutine()

        viewModel.uiState.test {
            val state = awaitItem()

            assertEquals("Should be back at first step", 0, state.currentStepIndex)
            assertFalse("Timer should not be running", state.timerRunning)
            assertFalse("Routine should not be complete", state.routineComplete)
        }
    }

    @Test
    fun `startStepTimer when already running should not start duplicate timer`() = runTest {
        viewModel.loadRoutine("quick-desk-stretch")
        viewModel.startStepTimer()

        // Try to start again
        viewModel.startStepTimer()

        viewModel.uiState.test {
            val state = awaitItem()

            // Should still have only one timer running
            assertTrue("Timer should be running", state.timerRunning)
        }
    }
}
