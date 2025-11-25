package com.dejvik.stretchhero.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home") // Added Home screen
    object StretchLibrary : Screen("stretch_library")
    object Achievements : Screen("achievements")
    object Settings : Screen("settings")
    object RoutineDetail : Screen("routine_detail/{routineId}") {
        fun createRoute(routineId: String) = "routine_detail/$routineId"
    }
}
