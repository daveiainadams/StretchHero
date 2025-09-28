package com.dejvik.stretchhero.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dejvik.stretchhero.ui.screens.HomeScreen
import com.dejvik.stretchhero.ui.theme.StretchHeroTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI tests for the main screens in StretchHero app.
 * Tests user interactions, navigation, and UI state changes.
 */
@RunWith(AndroidJUnit4::class)
class ScreensUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_displaysWelcomeContent() {
        composeTestRule.setContent {
            StretchHeroTheme {
                HomeScreen(onNavigateToLibrary = {})
            }
        }

        // Verify welcome text is displayed
        composeTestRule
            .onNodeWithText("Welcome to StretchHero")
            .assertIsDisplayed()
            
        // Verify subtitle text is displayed
        composeTestRule
            .onNodeWithText("Your personal stretching companion")
            .assertIsDisplayed()
    }

    @Test
    fun homeScreen_startButtonNavigatesToLibrary() {
        var navigatedToLibrary = false
        
        composeTestRule.setContent {
            StretchHeroTheme {
                HomeScreen(onNavigateToLibrary = { navigatedToLibrary = true })
            }
        }

        // Click the start button
        composeTestRule
            .onNodeWithText("Start Stretching")
            .performClick()

        // Verify navigation was triggered
        assert(navigatedToLibrary)
    }

    @Test
    fun homeScreen_hasAccessibleContent() {
        composeTestRule.setContent {
            StretchHeroTheme {
                HomeScreen(onNavigateToLibrary = {})
            }
        }

        // Verify the button has proper content description
        composeTestRule
            .onNodeWithContentDescription("Start stretching routine")
            .assertIsDisplayed()
    }

    // Additional UI test examples that would test actual components:
    /*
    @Test
    fun stretchLibraryScreen_displaysRoutines() {
        composeTestRule.setContent {
            StretchHeroTheme {
                StretchLibraryScreen(
                    routines = sampleRoutines,
                    onRoutineSelected = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText("Morning Energizer")
            .assertIsDisplayed()
            
        composeTestRule
            .onNodeWithText("Beginner")
            .assertIsDisplayed()
    }

    @Test
    fun stretchRoutineScreen_displaysTimerAndControls() {
        val mockViewModel = mockk<RoutineViewModel>()
        every { mockViewModel.uiState } returns MutableStateFlow(
            RoutineUiState(
                currentRoutine = sampleRoutine,
                currentStepIndex = 0,
                timeLeftInSeconds = 30,
                timerRunning = false
            )
        ).asStateFlow()

        composeTestRule.setContent {
            StretchHeroTheme {
                StretchRoutineScreen(
                    viewModel = mockViewModel,
                    onNavigateBack = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText("0:30")
            .assertIsDisplayed()
            
        composeTestRule
            .onNodeWithContentDescription("Start timer")
            .assertIsDisplayed()
    }

    @Test
    fun stretchRoutineScreen_timerControlsWork() {
        val mockViewModel = mockk<RoutineViewModel>()
        every { mockViewModel.uiState } returns MutableStateFlow(
            RoutineUiState(
                currentRoutine = sampleRoutine,
                currentStepIndex = 0,
                timeLeftInSeconds = 30,
                timerRunning = false
            )
        ).asStateFlow()
        
        every { mockViewModel.startStepTimer() } just Runs
        every { mockViewModel.pauseTimer() } just Runs

        composeTestRule.setContent {
            StretchHeroTheme {
                StretchRoutineScreen(
                    viewModel = mockViewModel,
                    onNavigateBack = {}
                )
            }
        }

        // Test play button
        composeTestRule
            .onNodeWithContentDescription("Start timer")
            .performClick()

        verify { mockViewModel.startStepTimer() }
    }

    @Test
    fun stretchRoutineScreen_showsErrorState() {
        val mockViewModel = mockk<RoutineViewModel>()
        every { mockViewModel.uiState } returns MutableStateFlow(
            RoutineUiState(
                error = RoutineError.RoutineNotFound
            )
        ).asStateFlow()

        composeTestRule.setContent {
            StretchHeroTheme {
                StretchRoutineScreen(
                    viewModel = mockViewModel,
                    onNavigateBack = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText("The requested routine could not be found")
            .assertIsDisplayed()
            
        composeTestRule
            .onNodeWithText("Retry")
            .assertIsDisplayed()
    }

    @Test
    fun stretchRoutineScreen_showsLoadingState() {
        val mockViewModel = mockk<RoutineViewModel>()
        every { mockViewModel.uiState } returns MutableStateFlow(
            RoutineUiState(isLoading = true)
        ).asStateFlow()

        composeTestRule.setContent {
            StretchHeroTheme {
                StretchRoutineScreen(
                    viewModel = mockViewModel,
                    onNavigateBack = {}
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Loading routine")
            .assertIsDisplayed()
    }

    @Test
    fun navigation_worksCorrectly() {
        var currentRoute = "home"
        
        composeTestRule.setContent {
            StretchHeroTheme {
                when (currentRoute) {
                    "home" -> HomeScreen(
                        onNavigateToLibrary = { currentRoute = "library" }
                    )
                    "library" -> StretchLibraryScreen(
                        routines = sampleRoutines,
                        onRoutineSelected = { currentRoute = "routine" }
                    )
                    "routine" -> StretchRoutineScreen(
                        viewModel = mockViewModel,
                        onNavigateBack = { currentRoute = "library" }
                    )
                }
            }
        }

        // Start at home
        composeTestRule
            .onNodeWithText("Welcome to StretchHero")
            .assertIsDisplayed()

        // Navigate to library
        composeTestRule
            .onNodeWithText("Start Stretching")
            .performClick()

        composeTestRule
            .onNodeWithText("Choose Your Routine")
            .assertIsDisplayed()

        // Navigate to routine
        composeTestRule
            .onNodeWithText("Morning Energizer")
            .performClick()

        // Should show routine screen
        composeTestRule
            .onNodeWithContentDescription("Timer display")
            .assertIsDisplayed()
    }
    */
    
    companion object {
        // Sample data for testing would go here
        // val sampleRoutines = listOf(...)
        // val sampleRoutine = Routine(...)
    }
}