package com.example.tmdb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.tmdb.navigation.BottomBarScreen
import com.example.tmdb.navigation.NavGraph
import com.example.tmdb.ui.theme.DeepBlue


@Composable
fun MainScreen(navController: NavHostController) {
    //val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navController = navController) }
    ) {
        NavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(BottomBarScreen.Home, BottomBarScreen.Favorites)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation() {
        screens.forEach() { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true
    BottomNavigationItem(
        modifier = Modifier
            .background(Color.White)
            .height(60.dp),
        label = {
            Text(
                text = screen.title,
                color = DeepBlue
            )
        },
        selected = selected,
        icon = {
            Icon(
                imageVector = if (selected) {
                    screen.selectedIcon
                } else {
                    screen.icon
                },
                contentDescription = null,
                tint = DeepBlue
            )
        },
        onClick = {
            navController.navigate(screen.route)
        }
    )
}
