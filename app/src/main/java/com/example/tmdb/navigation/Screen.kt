package com.example.tmdb.navigation


sealed class Screen(
    val route: String
) {
    object Details : Screen(
        route = "details"
    )
}
