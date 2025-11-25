package com.dejvik.stretchhero.navigation

import com.dejvik.stretchhero.navigation.Screen // Added import
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dejvik.stretchhero.ui.theme.montserratFont
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dejvik.stretchhero.ui.screens.HomeScreen // Added import
import com.dejvik.stretchhero.ui.screens.StretchLibraryScreen
import com.dejvik.stretchhero.ui.screens.StretchRoutineScreen
import com.dejvik.stretchhero.ui.screens.AchievementsScreen
import com.dejvik.stretchhero.ui.screens.OnboardingScreen
import com.dejvik.stretchhero.ui.screens.SettingsScreen
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.dejvik.stretchhero.data.UserPreferencesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun StretchHeroNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val repository = remember { UserPreferencesRepository(context) }
    val scope = rememberCoroutineScope()
    
    // Check if first launch
    var isFirstLaunch by remember { mutableStateOf<Boolean?>(null) }
    
    LaunchedEffect(Unit) {
        val progress = repository.userProgress.first()
        // If no routines completed and no achievements, show onboarding
        isFirstLaunch = progress.totalRoutinesCompleted == 0 && progress.unlockedAchievements.isEmpty()
    }
    
    if (isFirstLaunch == null) {
        // Loading
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }
    
    Scaffold(containerColor = MaterialTheme.colorScheme.background) { padding ->
        NavHost(
            navController = navController,
            startDestination = if (isFirstLaunch == true) Screen.Onboarding.route else Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Onboarding.route) {
                OnboardingScreen(
                    navController = navController,
                    onComplete = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(navController)
            }
            composable(Screen.StretchLibrary.route) {
                StretchLibraryScreen(navController)
            }
            composable(Screen.Settings.route) {
                SettingsScreen(navController)
            }
            composable(Screen.Achievements.route) {
                AchievementsScreen(navController)
            }
            composable(
                route = Screen.RoutineDetail.route,
                arguments = listOf(navArgument("routineId") { type = NavType.StringType })
            ) { backStackEntry ->
                val routineId = backStackEntry.arguments?.getString("routineId")
                if (routineId != null) {
                    // Pass only the routineId to StretchRoutineScreen
                    // The ViewModel inside StretchRoutineScreen will handle fetching the routine details
                    StretchRoutineScreen(navController = navController, routineId = routineId)
                } else {
                    // Handle routine ID not found or null
                    Text("Error: Routine ID missing or invalid.", fontFamily = montserratFont)
                }
            }
        }
    }
}
