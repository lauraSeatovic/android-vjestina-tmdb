package com.example.tmdb.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.tmdb.R
import com.example.tmdb.data.ButtonData
import com.example.tmdb.data.ContainerData
import com.example.tmdb.data.TopCastData
import com.example.tmdb.data.WritersData
import com.example.tmdb.navigation.RootScreen
import com.example.tmdb.repository.Movie
import com.example.tmdb.repository.listOfMovies
import com.example.tmdb.ui.common.Logo
import com.example.tmdb.viewModels.HomeViewModel
import org.koin.androidx.compose.viewModel


@Composable
fun HomeScreen(navController: NavController) {
    val homeViewModel: HomeViewModel by viewModel()
    homeViewModel.fetchFavorite()

    val favorites = homeViewModel.viewState.collectAsState()
    val popular by homeViewModel.getPopular().collectAsState(initial = emptyList())
    val free by homeViewModel.getTrending().collectAsState(initial = emptyList())
    val trending by homeViewModel.getFree().collectAsState(initial = emptyList())

    HomeLayout(
        favorites = favorites.value,
        popular = popular,
        free = free,
        trending = trending,
        onMovieCardClick = { id -> navController.navigate("details_screen/$id") },
        onFavoriteClick = { id -> homeViewModel.updateFavorite(id) }
    )

}

@Composable
fun HomeLayout(
    favorites: List<Movie>,
    popular: List<Movie>,
    free: List<Movie>,
    trending: List<Movie>,
    onMovieCardClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            Logo()
            SearchWidget()

            LazyColumn() {
                item {
                    val buttonList: List<ButtonData> = listOf(
                        ButtonData(1, "Streaming"),
                        ButtonData(2, "On Tv"),
                        ButtonData(3, "For rent"),
                        ButtonData(4, "In theaters")
                    )

                    val popular =
                        ContainerData("What's popular", buttonList, popular)
                    MoviesContainer(favorites, popular, onMovieCardClick, onFavoriteClick)

                    val buttonListFree: List<ButtonData> =
                        listOf(ButtonData(1, "Movies"), ButtonData(2, "TV"))

                    val free =
                        ContainerData("Free to Watch", buttonListFree, free)
                    MoviesContainer(favorites, free, onMovieCardClick, onFavoriteClick)

                    val buttonListTrending: List<ButtonData> =
                        listOf(ButtonData(1, "Today"), ButtonData(2, "This Week"))

                    val trending =
                        ContainerData("Trending", buttonListTrending, trending)
                    MoviesContainer(favorites, trending, onMovieCardClick, onFavoriteClick)

                    Spacer(modifier = Modifier.height(60.dp))

                }
            }
        }
    }
}



