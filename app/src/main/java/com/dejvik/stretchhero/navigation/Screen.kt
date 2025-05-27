package com.dejvik.stretchhero.navigation

sealed class Screen(val route: String) {
    object StretchLibrary : Screen("stretchLibrary")
    object RoutineDetail : Screen("routineDetail/{routineId}") {
        fun createRoute(routineId: String) = "routineDetail/$routineId"
    }
}
