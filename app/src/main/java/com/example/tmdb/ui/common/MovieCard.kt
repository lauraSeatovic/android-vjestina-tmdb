package com.example.tmdb.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.tmdb.repository.Movie

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCard(
    favorites: List<Movie>,
    movie: Movie,
    onMovieCardClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    height: Dp,
    width: Dp
) {
    val image = movie.image
    Card(modifier = Modifier
        .height(height) //179
        .width(width), //122
        shape = RoundedCornerShape(15.dp),
        onClick = { onMovieCardClick(movie.id) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.image}"),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.padding(5.dp)) {
                Heart(favorites, onFavoriteClick, movie)
            }
        }
    }
}