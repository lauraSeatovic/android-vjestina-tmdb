package com.example.tmdb.ui.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tmdb.data.Movie
import com.example.tmdb.data.listOfMovies
import com.example.tmdb.ui.common.MovieCard

@Composable
fun FavoriteGrid(movies: List<Movie>, onMovieCardClick: () -> Unit) {
    val quantity = movies.size
    var rows: Int = quantity / 3
    if (quantity % 3 != 0) {
        rows += 1
    }

    var counter = 0
    var counterRows = 0
    var counterColumns: Int
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        while (counterRows < rows) {
            counterColumns = 0
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                while ((counterColumns < 3) and (counter < quantity)) {

                    MovieCard(listOfMovies[counter].image, onMovieCardClick, 160.dp, 100.dp)

                    counterColumns += 1
                    counter += 1
                }
            }
            counterRows += 1
        }
    }
}