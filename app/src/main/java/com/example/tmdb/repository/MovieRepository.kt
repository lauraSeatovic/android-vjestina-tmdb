package com.example.tmdb.repository

import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun popularMovies(): Flow<List<Movie>>

    fun freeMovies(): Flow<List<Movie>>

    fun trendingMovies(): Flow<List<Movie>>

    fun favoriteMovies():  Flow<List<Movie>>

    fun movieDetails(movieId: Int): Flow<Movie>

    fun addMovieToFavorites(movie: Movie)

    fun updateFavorites(movieId: Int)

    fun showMovie(id: Int): Movie

    //fun movieSearchResults(query: String): Flow<List<Movie>>
}