package com.example.tmdb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tmdb.ui.screens.favorites.FavoritesScreen
import com.example.tmdb.ui.screens.homeScreen.HomeScreen

@Composable
fun NavGraph(
    rootNavHostController: NavHostController,
    bottomBarNavHostController: NavHostController
) {
    NavHost(
        navController = bottomBarNavHostController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(rootNavHostController)
        }

        composable(route = BottomBarScreen.Favorites.route) {
            FavoritesScreen(rootNavHostController)
        }
    }
}