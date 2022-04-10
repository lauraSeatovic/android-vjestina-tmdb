package com.example.tmdb.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tmdb.ButtonData
import com.example.tmdb.ContainerData
import com.example.tmdb.R


@Composable
fun HomeScreen(navController: NavController){
    val configuration = LocalConfiguration.current
    Box(modifier = Modifier
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
                    val movieList: List<Painter> = listOf(
                        painterResource(id = R.drawable.gattaca),
                        painterResource(id = R.drawable.ironman),
                        painterResource(id = R.drawable.gattaca)
                    )
                    val movieList2: List<Painter> = listOf(
                        painterResource(id = R.drawable.ironman2),
                        painterResource(id = R.drawable.godzilla),
                        painterResource(id = R.drawable.puppylove)
                    )
                    val movieListFree: List<Painter> = listOf(
                        painterResource(id = R.drawable.puppylove),
                        painterResource(id = R.drawable.junglebeat),
                        painterResource(id = R.drawable.gattaca)
                    )
                    val movieListTrending: List<Painter> = listOf(
                        painterResource(id = R.drawable.ironman2),
                        painterResource(id = R.drawable.godzilla),
                        painterResource(id = R.drawable.puppylove)
                    )
                    val popular: ContainerData =
                        ContainerData("What's popular", buttonList, listOf(movieList,movieList2, movieListFree, movieListTrending))
                    MoviesContainer(popular, navController)

                    val buttonListFree: List<ButtonData> =
                        listOf(ButtonData(1, "Movies"), ButtonData(2, "TV"))

                    val free: ContainerData =
                        ContainerData("Free to Watch", buttonListFree, listOf(movieListFree, movieList))
                    MoviesContainer(free, navController)

                    val buttonListTrending: List<ButtonData> =
                        listOf(ButtonData(1, "Today"), ButtonData(2, "This Week"))

                    val trending: ContainerData =
                        ContainerData("Trending", buttonListTrending, listOf(movieListTrending, movieListFree))
                    MoviesContainer(trending, navController)

                    Spacer(modifier = Modifier.height(60.dp))

                }
            }
        }
    }
}



