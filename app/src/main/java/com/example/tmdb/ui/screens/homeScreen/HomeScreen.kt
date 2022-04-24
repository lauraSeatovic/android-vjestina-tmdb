package com.example.tmdb.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tmdb.data.ButtonData
import com.example.tmdb.data.ContainerData
import com.example.tmdb.data.Movie
import com.example.tmdb.data.listOfMovies
import com.example.tmdb.navigation.Screen
import com.example.tmdb.ui.common.Logo


@Composable
fun HomeScreen(navController: NavController) {
    HomeLayout(
        listOfMovies = listOfMovies,
        onMovieCardClick = { navController.navigate(Screen.Details.route) },
        onFavoriteButtonClick = {}
    )

}

@Composable
fun HomeLayout(
    listOfMovies: List<Movie>,
    onMovieCardClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit
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
                        ContainerData("What's popular", buttonList, listOfMovies)
                    MoviesContainer(popular, onMovieCardClick)

                    val buttonListFree: List<ButtonData> =
                        listOf(ButtonData(1, "Movies"), ButtonData(2, "TV"))

                    val free =
                        ContainerData("Free to Watch", buttonListFree, listOfMovies)
                    MoviesContainer(free, onMovieCardClick)

                    val buttonListTrending: List<ButtonData> =
                        listOf(ButtonData(1, "Today"), ButtonData(2, "This Week"))

                    val trending =
                        ContainerData("Trending", buttonListTrending, listOfMovies)
                    MoviesContainer(trending, onMovieCardClick)

                    Spacer(modifier = Modifier.height(60.dp))

                }
            }
        }
    }
}



