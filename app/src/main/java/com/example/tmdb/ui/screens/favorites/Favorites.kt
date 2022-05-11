package com.example.tmdb.ui.screens.favorites

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tmdb.navigation.RootScreen
import com.example.tmdb.repository.Movie
import com.example.tmdb.repository.listOfMovies
import com.example.tmdb.ui.common.Logo
import com.example.tmdb.ui.common.MovieCard
import com.example.tmdb.ui.screens.details.Title
import com.example.tmdb.viewModels.FavoriteViewModel
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@Composable
fun FavoritesScreen(navController: NavController) {
    val favoriteViewModel: FavoriteViewModel by viewModel()
    favoriteViewModel.fetchFavorite()

    val favorites = favoriteViewModel.viewState.collectAsState()

    FavoritesLayout(
        listOfMovies = favorites.value,
        onMovieCardClick = { id -> navController.navigate("details_screen/$id") },
        onFavoriteClick = { id -> favoriteViewModel.updateFavorite(id) }
    )
}

@Composable
fun FavoritesLayout(
    listOfMovies: List<Movie>,
    onMovieCardClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,

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
                        FavoriteGrid(listOfMovies, onMovieCardClick, onFavoriteClick)
                    }

                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
        }
    }
}
