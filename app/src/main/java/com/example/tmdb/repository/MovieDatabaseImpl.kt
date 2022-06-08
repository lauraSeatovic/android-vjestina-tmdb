package com.example.tmdb.repository

class MovieDatabaseImpl : MovieDatabase {
    val favoriteMoviesSet: MutableSet<Movie> = mutableSetOf()

    val allMovies: MutableList<Movie> = mutableListOf()
    override fun getFavoriteMovies(): Set<Movie> {
        return favoriteMoviesSet
    }

    override fun getMovies(): List<Movie> {
        return allMovies
    }

    override fun saveFavoriteMovie(movie: Movie) {
        if (favoriteMoviesSet.contains(movie)) {
            favoriteMoviesSet.remove(movie)
        } else {
            favoriteMoviesSet.add(movie)
        }
    }

    override fun getMovieById(id: Int): Movie {
        for (movie in allMovies) {
            if (movie.id == id) {
                return movie
            }
        }
        return Movie()
    }

    override fun addMovies(movies: List<Movie>) {
        for (movie in movies) {
            if (!allMovies.contains(movie)) {
                allMovies.add(movie)
            }
        }

    }
}