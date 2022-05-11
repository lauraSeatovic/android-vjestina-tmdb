package com.example.tmdb.repository

import android.util.Log
import com.example.tmdb.R
import com.example.tmdb.data.TopCastData
import com.example.tmdb.data.WritersData

class MovieDatabaseImpl : MovieDatabase {
    val favoriteMoviesSet: MutableSet<Movie> = mutableSetOf<Movie>()

    val allMovies: List<Movie> = listOfMovies
    override fun getFavoriteMovies(): Set<Movie> {
        return favoriteMoviesSet
    }

    override fun getMovies(): List<Movie> {
        return allMovies
    }

    override fun getPopular(): List<Movie> {
        var movies = getMovies()
        var popular: MutableList<Movie> = mutableListOf()
        for (movie in movies) {
            if (movie.popular) {
                popular.add(movie)
            }
        }
        return popular
    }

    override fun getFree(): List<Movie> {
        var movies = getMovies()
        var free: MutableList<Movie> = mutableListOf()
        for (movie in movies) {
            if (movie.free) {
                free.add(movie)
            }
        }
        return free
    }

    override fun getTrending(): List<Movie> {
        var movies = getMovies()
        var trending: MutableList<Movie> = mutableListOf()
        for (movie in movies) {
            if (movie.trending) {
                trending.add(movie)
            }
        }
        return trending
    }

    override fun saveFavoriteMovie(movie: Movie) {
        if (favoriteMoviesSet.contains(movie)) {
            favoriteMoviesSet.remove(movie)
        } else {
            favoriteMoviesSet.add(movie)
        }
    }

    override fun getMovieById(id: Int): Movie{
        for(movie in allMovies){
            if(movie.id == id){
                return movie
            }
        }
        return Movie()
    }
}