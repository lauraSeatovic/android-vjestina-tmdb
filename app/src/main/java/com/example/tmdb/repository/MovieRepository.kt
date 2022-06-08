package com.example.tmdb.repository

import com.example.tmdb.data.DbMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun popularMovies(): Flow<List<Movie>>
    fun favoriteMovies(): Flow<List<DbMovie>>
    fun movieDetails(movieId: Int): Flow<MovieDetails>
    suspend fun updateFavorites(movieId: Int)
    fun nowPlaying(): Flow<List<Movie>>
    fun upcoming(): Flow<List<Movie>>
    fun topRated(): Flow<List<Movie>>
    fun getInput(input: String): Flow<List<Movie>>
}