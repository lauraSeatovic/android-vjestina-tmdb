package com.example.tmdb.repository

import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun popularMovies(): Flow<List<Movie>>
    fun favoriteMovies(): Flow<List<Movie>>
    fun movieDetails(movieId: Int): Flow<MovieDetails>
    fun addMovieToFavorites(movie: Movie)
    fun updateFavorites(movieId: Int)
    fun showMovie(id: Int): Movie
    fun nowPlaying(): Flow<List<Movie>>
    fun upcoming(): Flow<List<Movie>>
    fun topRated(): Flow<List<Movie>>
    fun getInput(input: String): Flow<List<Movie>>
}