package com.example.tmdb.data

import androidx.compose.ui.graphics.painter.Painter
import com.example.tmdb.data.ButtonData
import com.example.tmdb.repository.Movie

class ContainerData(
    val title: String,
    val buttons: List<ButtonData>,
    val movies: List<Movie>
)
