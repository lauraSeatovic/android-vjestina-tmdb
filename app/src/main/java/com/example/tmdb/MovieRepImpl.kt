package com.example.tmdb

import com.example.tmdb.rep.Movie
import com.example.tmdb.rep.listOfMovies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MovieRep(private val movieDatabase: MovieDatabase) {

    var detalis:Movie = Movie()

    var filmovi: List<Movie> = listOfMovies
    val favoriti: MutableList<Movie> = ArrayList()

    var favoriti2: Flow<Movie> = movieDatabase.getFavoriteMovies().asFlow()

    fun favouriteMovies(): List<Movie> = movieDatabase.getFavoriteMovies()
    fun updateDetails(): Movie = detalis

    fun favouriteMovies2(): Flow<Movie> = favoriti2
    fun addFavorite(movie: Movie) {
        movieDatabase.addFavorite(movie)
        favoriti2 = movieDatabase.getFavoriteMovies().asFlow()

    }

    fun removeFavorite(movie:Movie){
        movieDatabase.removeFavorite(movie)
        favoriti2 = movieDatabase.getFavoriteMovies().asFlow()
    }

    fun updateDetails(movie:Movie){
        detalis = movie
    }



}

