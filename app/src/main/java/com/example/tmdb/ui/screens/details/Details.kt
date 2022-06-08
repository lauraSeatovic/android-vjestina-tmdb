package com.example.tmdb.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.tmdb.repository.MovieDetails
import com.example.tmdb.ui.common.Logo
import com.example.tmdb.ui.common.Title
import com.example.tmdb.ui.theme.DeepBlue
import com.example.tmdb.viewModels.DetailsViewModel
import org.koin.androidx.compose.viewModel


@Composable
fun DetailsScreen(navController: NavController, id: Int?) {
    val detailsViewModel: DetailsViewModel by viewModel()

    var showMovie: MovieDetails?
    val movieDb by detailsViewModel.showMovieDb(id!!).collectAsState(initial = null)
    showMovie = movieDb
    if (movieDb == null) {
        val movie by detailsViewModel.showMovie(id!!).collectAsState(initial = null)
        showMovie = movie
    }
    DetailsLayout(
        movie = showMovie,
        onBackIconClick = { navController.navigateUp() }
    )
}

@Composable()
fun DetailsLayout(
    movie: MovieDetails?,
    onBackIconClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                Box() {
                    Logo()
                    IconButton(onClick = { onBackIconClick() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
                if (movie != null) {
                    MainImageDetails(
                        painter = rememberAsyncImagePainter(movie.image),
                        title = movie.title,
                        year = 2000,
                        date = movie.releaseDate,
                        genre = movie.genres.toString(),
                        duration = movie.runtime,
                        score = movie.vote
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp)
                ) {
                    Title("Overview")
                }
                if (movie != null) {
                    MovieDescription(description = movie.overview)
                    WritersGrid(movie.fullCrew)
                    CastRow(castList = listOf(movie.mainCast, movie.fullCrew))
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}


@Composable
private fun MovieDescription(description: String) {
    Text(
        text = description,
        color = Color.Black,
        fontSize = 17.sp,
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp, end = 20.dp)
    )
}

