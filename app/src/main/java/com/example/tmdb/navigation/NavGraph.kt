package com.example.tmdb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tmdb.navigation.BottomBarScreen
import com.example.tmdb.navigation.Screen
import com.example.tmdb.ui.screens.details.Details
import com.example.tmdb.ui.screens.favorites.Favorites
import com.example.tmdb.ui.screens.homeScreen.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }

        composable(route = BottomBarScreen.Favorites.route) {
            Favorites(navController)
        }

        composable(route = Screen.Details.route) {
            Details(navController)
        }
    }
}