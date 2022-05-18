package com.example.tmdb.repository

interface MovieDatabase {
    fun saveFavoriteMovie(movie: Movie)
    fun getFavoriteMovies(): Set<Movie>
    fun getMovies(): List<Movie>
    fun getMovieById(id: Int): Movie
    fun addMovies(movies: List<Movie>)
}