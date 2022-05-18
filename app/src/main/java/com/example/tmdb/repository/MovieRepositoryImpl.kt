package com.example.tmdb.repository

import android.util.Log
import com.example.tmdb.api.MovieApiImpl
import com.example.tmdb.api.MovieListResponse
import com.example.tmdb.api.MovieResponse
import com.example.tmdb.data.CrewData
import kotlinx.coroutines.flow.*


class MovieRepositoryImpl(
    private val movieDatabase: MovieDatabaseImpl,
    private val movieApi: MovieApiImpl
) : MovieRepository {
    override fun popularMovies(): Flow<List<Movie>> = flow {
        val popular: MutableList<Movie> = mutableListOf()
        val movies = movieApi.getPopularMovies().movies
        for (movie in movies) {
            popular.add(Movie(id = movie.id, title = movie.title, image = movie.posterPath))
        }
        movieDatabase.addMovies(popular)
        emit(popular)
    }

    override fun nowPlaying(): Flow<List<Movie>> = flow {
        val nowPlaying: MutableList<Movie> = mutableListOf()
        val movies = movieApi.getNowPlayingMovies().movies
        for (movie in movies) {
            nowPlaying.add(Movie(id = movie.id, title = movie.title, image = movie.posterPath))
        }
        movieDatabase.addMovies(nowPlaying)
        emit(nowPlaying)
    }

    override fun upcoming(): Flow<List<Movie>> = flow {
        val upcoming: MutableList<Movie> = mutableListOf()
        val movies = movieApi.getUpcomingMovies().movies
        for (movie in movies) {
            upcoming.add(Movie(id = movie.id, title = movie.title, image = movie.posterPath))
        }
        movieDatabase.addMovies(upcoming)
        emit(upcoming)
    }

    override fun topRated(): Flow<List<Movie>> = flow {
        val topRated: MutableList<Movie> = mutableListOf()
        val movies = movieApi.getTopRatedMovies().movies
        for (movie in movies) {
            topRated.add(Movie(id = movie.id, title = movie.title, image = movie.posterPath))
        }
        movieDatabase.addMovies(topRated)
        emit(topRated)
    }

    override fun favoriteMovies(): Flow<List<Movie>> =
        flow {
            val favorite = movieDatabase.getFavoriteMovies().toList()
            emit(favorite)
        }


    override fun movieDetails(movieId: Int): Flow<MovieDetails> =
        flow {
            val movie = movieApi.getDetails(movieId)

            val genres = mutableListOf<String>()
            for (genre in movie.genres) {
                genres.add(genre.name)
            }

            val credits = movieApi.getCredits(movieId)

            val mainCast = mutableListOf<CrewData>()
            val fullCrew = mutableListOf<CrewData>()

            for (crew in credits.crew) {
                fullCrew.add(CrewData(crew.profilePath, crew.name, crew.job))
            }

            for (crew in credits.cast) {
                if (crew.order <= 6)
                    mainCast.add(CrewData(crew.profilePath, crew.name, crew.character))
                fullCrew.add(CrewData(crew.profilePath, crew.name, crew.character))
            }


            val movieDetails = MovieDetails(
                movie.id,
                movie.posterPath,
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
                        image = movie.posterPath,
                        overview = movie.overview
                    )
                )
            }
        }
        emit(result)
    }
}

