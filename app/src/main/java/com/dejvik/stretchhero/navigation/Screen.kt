package com.dejvik.stretchhero.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home") // Added Home screen
    object StretchLibrary : Screen("stretchLibrary")
    object Achievements : Screen("achievements")
    object RoutineDetail : Screen("routineDetail/{routineId}") {
        fun createRoute(routineId: String) = "routineDetail/$routineId"
    }
}
