package com.example.tmdb.viewModels

import com.example.tmdb.repository.Movie

data class FavoriteViewState(
    val favorite: List<Movie> = emptyList()
)