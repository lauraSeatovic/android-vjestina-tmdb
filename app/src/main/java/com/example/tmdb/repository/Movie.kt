package com.example.tmdb.repository

import com.example.tmdb.data.CrewData


data class Movie(
    val id: Int = -1,
    val image: String? = "",
    val title: String = "",
    val overview: String = ""
)

data class MovieDetails(
    val id: Int,
    val image: String?,
    val title: String,
    val releaseDate: String,
    val vote: Float,
    val genres: List<String>,
    val runtime: Int,
    val overview: String,
    val mainCast: List<CrewData>,
    val fullCrew: List<CrewData>
)

