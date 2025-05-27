package com.dejvik.stretchhero.navigation

import com.dejvik.stretchhero.navigation.Screen // Added import
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dejvik.stretchhero.ui.screens.StretchLibraryScreen
import com.dejvik.stretchhero.ui.screens.StretchRoutineScreen
import com.dejvik.stretchhero.ui.theme.DarkGray

@Composable
fun StretchHeroNavHost() {
    val navController = rememberNavController()
    Scaffold(containerColor = DarkGray) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.StretchLibrary.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.StretchLibrary.route) {
                StretchLibraryScreen(navController)
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
                    Text("Error: Routine ID missing or invalid.")
                }
            }
        }
    }
}
