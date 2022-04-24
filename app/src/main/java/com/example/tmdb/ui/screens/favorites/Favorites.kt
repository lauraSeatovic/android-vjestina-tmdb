package com.example.tmdb.ui.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tmdb.data.Movie
import com.example.tmdb.data.listOfMovies
import com.example.tmdb.navigation.Screen
import com.example.tmdb.ui.common.Logo
import com.example.tmdb.ui.screens.details.Title

@Composable
fun Favorites(navController: NavController) {
    FavoritesLayout(
        listOfMovies = listOfMovies,
        onMovieCardClick = { navController.navigate(Screen.Details.route) }
    )

}

@Composable
fun FavoritesLayout(
    listOfMovies: List<Movie>,
    onMovieCardClick: () -> Unit,

    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Logo()
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 20.dp)
            ) {
                Title("Favorites")
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally)

            ) {
                item {

                    Box(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                    ) {
                        FavoriteGrid(listOfMovies, onMovieCardClick)
                    }

                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
        }
    }
}


