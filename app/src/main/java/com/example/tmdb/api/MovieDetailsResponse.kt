package com.example.tmdb.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("overview")
    val overview: String,
    @SerialName("genres")
    val genres: List<GenresResponse>,
    @SerialName("original_language")
    val language: String,
    @SerialName("vote_average")
    val vote: Float,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("runtime")
    val runtime: Int,

    )

@Serializable
data class GenresResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)