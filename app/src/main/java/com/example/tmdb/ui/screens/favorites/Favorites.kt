package com.example.tmdb.ui.screens.favorites


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tmdb.repository.Movie
import com.example.tmdb.ui.common.Logo
import com.example.tmdb.ui.common.Title
import com.example.tmdb.viewModels.FavoriteViewModel
import org.koin.androidx.compose.viewModel

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesLayout(
    listOfMovies: List<Movie>,
    onMovieCardClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,

    ) {
    Box(
        modifier = Modifier
            .background(Color.White)

    ) {
        Column(

        ) {
            Logo()
            Box(modifier = Modifier.padding(10.dp)) {
                Title("Favorites")
            }
            FavoriteGrid(movies = listOfMovies, onMovieCardClick =onMovieCardClick , onFavoriteClick = onFavoriteClick)
        }
    }
}
