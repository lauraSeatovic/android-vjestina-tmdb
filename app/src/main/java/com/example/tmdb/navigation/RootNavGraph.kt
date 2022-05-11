package com.example.tmdb.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tmdb.MainScreen
import com.example.tmdb.ui.screens.details.DetailsScreen

@Composable
fun RootNavGraph(rootNavHostController: NavHostController) {
    NavHost(rootNavHostController, startDestination = RootScreen.Main.route) {
        composable(RootScreen.Main.route) {
            MainScreen(rootNavHostController)
        }
        composable(
            route = RootScreen.Details.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { entry -> DetailsScreen(rootNavHostController, id = entry.arguments?.getInt("id"))
        }
    }
}