package com.example.tmdb.ui.screens.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tmdb.repository.Movie
import com.example.tmdb.ui.common.MovieCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteGrid(
    movies: List<Movie>,
    onMovieCardClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxHeight(0.9f)

    ) {
        items(movies.size) { index ->
            MovieCard(
                favorites = movies,
                movie = movies[index],
                onMovieCardClick = onMovieCardClick,
                onFavoriteClick = onFavoriteClick,
                height = 160.dp,
                width = 100.dp
            )
        }
    }
}