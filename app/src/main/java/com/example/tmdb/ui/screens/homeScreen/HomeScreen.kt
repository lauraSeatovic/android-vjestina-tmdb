package com.example.tmdb.ui.screens.homeScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tmdb.data.ButtonData
import com.example.tmdb.data.ContainerData
import com.example.tmdb.navigation.RootScreen
import com.example.tmdb.repository.Movie
import com.example.tmdb.ui.common.Logo
import com.example.tmdb.viewModels.HomeViewModel
import org.koin.androidx.compose.viewModel


@Composable
fun HomeScreen(navController: NavController) {
    val homeViewModel: HomeViewModel by viewModel()
    homeViewModel.fetchFavorite()

    val favorites = homeViewModel.viewState.collectAsState()
    val popular by homeViewModel.getPopular().collectAsState(initial = emptyList())
    val nowPlaying by homeViewModel.getNowPlaying().collectAsState(initial = emptyList())
    val topRated by homeViewModel.getTopRated().collectAsState(initial = emptyList())
    val upcoming by homeViewModel.getUpcoming().collectAsState(initial = emptyList())


    HomeLayout(
        favorites = favorites.value,
        popular = popular,
        nowPlaying = nowPlaying,
        topRated = topRated,
        upcoming = upcoming,
        onMovieCardClick = { id -> navController.navigate("details_screen/$id") },
        onFavoriteClick = { id -> homeViewModel.updateFavorite(id) },
        onSearchClick = {navController.navigate(RootScreen.Search.route) }
    )

}

@Composable
fun HomeLayout(
    favorites: List<Movie>,
    popular: List<Movie>,
    nowPlaying: List<Movie>,
    topRated: List<Movie>,
    upcoming: List<Movie>,
    onMovieCardClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    onSearchClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            Logo()
            HomeSearchWidget(onSearchClick)

            LazyColumn() {
                item {
                    val buttonList: List<ButtonData> = listOf(
                        ButtonData(1, "Streaming"),
                        ButtonData(2, "On Tv"),
                        ButtonData(3, "For rent"),
                        ButtonData(4, "In theaters")
                    )
                    val popular = ContainerData("What's popular", buttonList, popular)
                    MoviesContainer(favorites, popular, onMovieCardClick, onFavoriteClick)

                    val buttonListNowPlaying: List<ButtonData> =
                        listOf(ButtonData(1, "Movies"), ButtonData(2, "TV"))
                    val nowPlaying = ContainerData("Now Playing", buttonListNowPlaying, nowPlaying )
                    MoviesContainer(favorites, nowPlaying, onMovieCardClick, onFavoriteClick)

                    val buttonListTopRated: List<ButtonData> =
                        listOf(ButtonData(1, "Today"), ButtonData(2, "This Week"))
                    val topRated = ContainerData("Top rated", buttonListTopRated, topRated)
                    MoviesContainer(favorites, topRated, onMovieCardClick, onFavoriteClick)

                    val buttonListUpcoming: List<ButtonData> =
                        listOf(ButtonData(1, "Today"), ButtonData(2, "This Week"))
                    val upcoming = ContainerData("Upcoming", buttonListUpcoming, upcoming)
                    MoviesContainer(favorites, upcoming, onMovieCardClick, onFavoriteClick)

                    Spacer(modifier = Modifier.height(60.dp))

                }
            }
        }
    }
}



