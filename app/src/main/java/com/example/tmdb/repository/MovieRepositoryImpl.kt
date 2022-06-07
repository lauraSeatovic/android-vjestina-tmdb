package com.example.tmdb.repository

import android.util.Log
import com.example.tmdb.api.MovieApiImpl
import com.example.tmdb.api.MovieListResponse
import com.example.tmdb.api.MovieResponse
import com.example.tmdb.data.CrewData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*


class MovieRepositoryImpl(
    private val movieDatabase: MovieDatabaseImpl,
    private val movieApi: MovieApiImpl
) : MovieRepository {
    private val popularMoviesFlow = flow {
        val movies = movieApi.getPopularMovies().movies.map { (Movie(id = it.id, title = it.title, image = "https://image.tmdb.org/t/p/w500${it.posterPath}")) }
        movieDatabase.addMovies(movies)
        emit(movies)
    }


    override fun popularMovies(): Flow<List<Movie>> = popularMoviesFlow


    private val nowPlayingMoviesFlow = flow {
        val movies = movieApi.getNowPlayingMovies().movies.map { (Movie(id = it.id, title = it.title, image = "https://image.tmdb.org/t/p/w500${it.posterPath}")) }
        movieDatabase.addMovies(movies)
        emit(movies)
    }

    override fun nowPlaying(): Flow<List<Movie>> = nowPlayingMoviesFlow

    private val upcomingMoviesFlow = flow {
        val movies = movieApi.getUpcomingMovies().movies.map { (Movie(id = it.id, title = it.title, image = "https://image.tmdb.org/t/p/w500${it.posterPath}")) }
        movieDatabase.addMovies(movies)
        emit(movies)
    }

    override fun upcoming(): Flow<List<Movie>> = upcomingMoviesFlow

    private val topRatedMoviesFlow = flow {
        val movies = movieApi.getTopRatedMovies().movies.map { (Movie(id = it.id, title = it.title, image = "https://image.tmdb.org/t/p/w500${it.posterPath}")) }
        movieDatabase.addMovies(movies)
        emit(movies)
    }

    override fun topRated(): Flow<List<Movie>> = topRatedMoviesFlow

    override fun favoriteMovies(): Flow<List<Movie>> =
        flow {
            val favorite = movieDatabase.getFavoriteMovies().toList()
            emit(favorite)
        }


    override fun movieDetails(movieId: Int): Flow<MovieDetails> =
        flow {
            val movie = movieApi.getDetails(movieId)

            val genres = movie.genres.map { it.name }

            val credits = movieApi.getCredits(movieId)

            val mainCast = mutableListOf<CrewData>()
            val fullCrew = mutableListOf<CrewData>()

            for (crew in credits.crew) {
                fullCrew.add(CrewData("https://image.tmdb.org/t/p/w500${crew.profilePath}", crew.name, crew.job))
            }

            for (crew in credits.cast) {
                if (crew.order <= 6)
                    mainCast.add(CrewData("https://image.tmdb.org/t/p/w500${crew.profilePath}", crew.name, crew.character))
                fullCrew.add(CrewData("https://image.tmdb.org/t/p/w500${crew.profilePath}", crew.name, crew.character))
            }


            val movieDetails = MovieDetails(
                movie.id,
                "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                movie.title,
                movie.releaseDate,
                movie.vote,
                genres,
                movie.runtime,
                movie.overview,
                mainCast,
                fullCrew
            )
            emit(movieDetails)
        }

    override fun showMovie(id: Int): Movie {
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

    override fun getInput(input: String): Flow<List<Movie>> = flow {
        var result: MutableList<Movie> = mutableListOf()
        if (input != "") {
            val movies = movieApi.search(input).movies
            for (movie in movies) {
                result.add(
                    Movie(
                        id = movie.id,
                        title = movie.title,
                        image = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                        overview = movie.overview
                    )
                )
            }
        }
        emit(result)
    }
}

