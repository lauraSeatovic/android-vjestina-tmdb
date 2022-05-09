package com.example.tmdb.ui.screens.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tmdb.data.Movie
import com.example.tmdb.data.listOfMovies
import com.example.tmdb.ui.common.Logo
import com.example.tmdb.ui.theme.DeepBlue


@Composable
fun DetailsScreen(navController: NavController) {
    DetailsLayout(
        movie = listOfMovies[0],
        onBackIconClick = { navController.navigateUp() }
    )
}

@Composable()
fun DetailsLayout(
    movie: Movie,
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
                MainImageDetails(
                    painter = painterResource(movie.image),
                    movie.title,
                    movie.year,
                    movie.date,
                    movie.genre,
                    movie.duration,
                    movie.score.toFloat()
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp)
                ) {
                    Title("Overview")
                }
                MovieDescription(description = movie.description)
                WritersGrid(
                    quantity = movie.authors.size,
                    names = movie.authors
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Title(title = "Top Billed Cast")
                    Title2(title = "Full Cast & Crew")
                }

                CastRow(castList = movie.topCast)

                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}


@Composable
fun Title(title: String) {
    Text(
        text = title,
        color = DeepBlue,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun Title2(title: String) {
    Text(
        text = title,
        color = DeepBlue,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun MovieDescription(description: String) {
    Text(
        text = description,
        color = Color.Black,
        fontSize = 17.sp,
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp)
    )
}

