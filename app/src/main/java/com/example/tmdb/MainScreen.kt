package com.example.tmdb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.tmdb.ui.MovieCard
import com.example.tmdb.ui.theme.DeepBlue


@Composable
fun MainScreen(){
    val navController = rememberNavController( )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {BottomBar(navController = navController)}
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(BottomBarScreen.Home, BottomBarScreen.Favorites)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation() {
        screens.forEach(){ screen ->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }
}



@Composable
fun RowScope.AddItem(
    screen : BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        modifier = Modifier
            .background(Color.White)
            .height(60.dp)
        ,
        label = {
            Text(
                text = screen.title,
                color = DeepBlue
            )
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = null,
                tint = DeepBlue
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        }==true,
        onClick = {
            navController.navigate(screen.route)
        }
    )
}
