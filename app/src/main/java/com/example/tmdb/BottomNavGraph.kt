package com.example.tmdb

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tmdb.ui.Details
import com.example.tmdb.ui.Favorites
import com.example.tmdb.ui.HomeScreen

@Composable
fun BottomNavGraph(navController: NavHostController){
        NavHost(
            navController = navController,
            startDestination = BottomBarScreen.Home.route
        ){
            composable(route = BottomBarScreen.Home.route){
                HomeScreen(navController)
            }

            composable(route = BottomBarScreen.Favorites.route){
                Favorites(navController)
            }
            composable(route = Screen.Details.route){
                Details(navController)
            }
        }
}