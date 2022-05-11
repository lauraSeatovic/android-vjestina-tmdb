package com.example.tmdb.repository

interface MovieDatabase {
    fun saveFavoriteMovie(movie : Movie)

    fun getFavoriteMovies() : Set<Movie>

    fun getMovies() : List<Movie>

    fun getPopular() : List<Movie>

    fun getFree() : List<Movie>

    fun getTrending() : List<Movie>

    fun getMovieById(id: Int) : Movie
}