package com.dejvik.stretchhero.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dejvik.stretchhero.ui.screens.StretchRoutineScreen
import com.dejvik.stretchhero.ui.theme.DarkGray
import com.dejvik.stretchhero.ui.screens.StretchLibraryScreen

@Composable
fun StretchHeroNavHost() {
    val navController = rememberNavController()
    Scaffold(containerColor = DarkGray) { padding ->
        NavHost(
            navController = navController,
            startDestination = "stretchLibrary",
            modifier = Modifier.padding(padding)
        ) {
            composable("stretchLibrary") {
                StretchLibraryScreen(navController)
            }
            composable(
                "routineDetail/{routineName}",
                arguments = listOf(navArgument("routineName") { type = NavType.StringType })
            ) { backStackEntry ->
                val routineName = backStackEntry.arguments?.getString("routineName") ?: "Unknown"
                StretchRoutineScreen(routineName = routineName)
            }
        }
    }
}
