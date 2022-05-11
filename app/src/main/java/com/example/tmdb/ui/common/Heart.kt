package com.example.tmdb.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.repository.Movie
import com.example.tmdb.ui.theme.DeepBlue

@Composable
fun Heart(favorites: List<Movie>, onFavoriteClick: (Int) -> Unit, movie: Movie) {
    Box(
        modifier = Modifier
            .size(35.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .background(DeepBlue.copy(alpha = 0.6f)),
    ) {
        var isFavorite = favorites.contains(movie)

        IconButton(
            onClick = {onFavoriteClick(movie.id)}

        ) {
            Icon(
                imageVector = if (isFavorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.Center)

            )
        }
    }
}