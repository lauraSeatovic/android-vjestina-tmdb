package com.example.tmdb.navigation


sealed class RootScreen(
    val route: String,
    val title: String
) {
    object Main : RootScreen(
        route = "main",
        title = "Main"
    )

    object Details : RootScreen(
        route = "details_screen/{id}",
        title = "Details"
    )

    object Search : RootScreen(
        route = "search_screen",
        title = "Search"
    )

}
