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
            assertTrue(state.error is RoutineError.RoutineNotFound)
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
}
