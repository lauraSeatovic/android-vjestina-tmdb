package com.example.tmdb.repository

import android.util.Log
import kotlinx.coroutines.flow.*


class MovieRepositoryImpl(private val movieDatabase: MovieDatabaseImpl) : MovieRepository {
    override fun popularMovies(): Flow<List<Movie>> =
        flow {
            val popular = movieDatabase.getPopular()
            emit(popular)
        }

    override fun freeMovies(): Flow<List<Movie>> =
        flow {
            val popular = movieDatabase.getFree()
            emit(popular)
        }


    override fun trendingMovies(): Flow<List<Movie>> =
        flow {
            val popular = movieDatabase.getTrending()
            emit(popular)
        }


    override fun favoriteMovies(): Flow<List<Movie>> =
        flow {
            val favorite = movieDatabase.getFavoriteMovies().toList()
            emit(favorite)
        }


    override fun movieDetails(movieId: Int): Flow<Movie> =
        flow {
            val movie = movieDatabase.getMovieById(movieId)
            emit(movie)
        }

    override fun showMovie(id: Int): Movie{
        return movieDatabase.getMovieById(id)
    }


    override fun addMovieToFavorites(movie: Movie) {
        movieDatabase.saveFavoriteMovie(movie)
    }

    override fun updateFavorites(movieId: Int) {
        var movies = movieDatabase.getMovies()
        for (movie in movies) {
            if (movie.id == movieId) {
                movieDatabase.saveFavoriteMovie(movie)
            }
        }
    }
}

