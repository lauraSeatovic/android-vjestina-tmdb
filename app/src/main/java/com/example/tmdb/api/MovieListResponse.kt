package com.example.tmdb.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieListResponse(
    @SerialName("results")
    val movies: List<MovieResponse>
)
