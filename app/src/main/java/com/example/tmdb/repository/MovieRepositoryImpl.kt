package com.example.tmdb.repository

import android.util.Log
import com.example.tmdb.api.MovieApiImpl
import com.example.tmdb.data.*
import kotlinx.coroutines.flow.*


class MovieRepositoryImpl(
    private val database: AppDatabase,
    private val movieApi: MovieApiImpl
) : MovieRepository {

    val dao = database.Dao()

    @Throws(NullPointerException::class)
    fun getMovieFromDb(movieId: Int): Flow<MovieDetails?>{
        val movieDetails = flow {
            val movie = dao.getMovie(movieId.toString())
            if(movie==null) emit(null)
            else{
            val crew =
                dao.getMovieCrew(movieId.toString()).map { CrewData(it.image, it.name, it.job) }
            val cast =
                dao.getMovieCast(movieId.toString()).map { CrewData(it.image, it.name, it.role) }

            val movieDetails = MovieDetails(
                movie.id,
                "https://image.tmdb.org/t/p/w500${movie.image}",
                movie.title,
                movie.releaseDate,
                movie.vote,
                emptyList(),
                movie.runtime,
                movie.overview,
                cast,
                crew
            )
            emit(movieDetails)}
        }


        return movieDetails
    }

    suspend fun movieDetailsDb(movieId: Int): DbMovie{
            val movie = movieApi.getDetails(movieId)

            val genres = movie.genres.map { it.name }

            val credits = movieApi.getCredits(movieId)

            val mainCast = mutableListOf<CrewData>()
            val fullCrew = mutableListOf<CrewData>()

            for (crew in credits.crew) {
                dao.insertMoviesCrew(DbMoviesCrew(movieId.toString(), crew.name, "https://image.tmdb.org/t/p/w500${crew.profilePath}", crew.job))
                dao.insertCrew(
                    DbCrew(
                        crew.name,
                        "https://image.tmdb.org/t/p/w500${crew.profilePath}",
                        crew.job
                    )
                )
                fullCrew.add(CrewData("https://image.tmdb.org/t/p/w500${crew.profilePath}", crew.name, crew.job))
            }

            for (crew in credits.cast) {
                if (crew.order <= 6) {
                    dao.insertMoviesCast(DbMoviesCast(movieId.toString(), crew.name, "https://image.tmdb.org/t/p/w500${crew.profilePath}", crew.character))
                    dao.insertCast(
                        DbCast(
                            crew.name,
                            "https://image.tmdb.org/t/p/w500${crew.profilePath}",
                            crew.character
                        )
                    )
                    mainCast.add(
                        CrewData(
                            "https://image.tmdb.org/t/p/w500${crew.profilePath}",
                            crew.name,
                            crew.character
                        )
                    )
                }
                dao.insertMoviesCrew(DbMoviesCrew(movieId.toString(), crew.name, "https://image.tmdb.org/t/p/w500${crew.profilePath}", crew.character))
                dao.insertCrew(
                    DbCrew(
                        crew.name,
                        "https://image.tmdb.org/t/p/w500${crew.profilePath}",
                        crew.character
                    )
                )

                fullCrew.add(CrewData("https://image.tmdb.org/t/p/w500${crew.profilePath}", crew.name, crew.character))
            }


            val movieDetails = DbMovie(
                movie.id,
                "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                movie.title,
                movie.releaseDate,
                movie.vote,
                movie.runtime,
                movie.overview
            )
            return movieDetails
        }

    private suspend fun insertMovieToDatabase(movieId: Int){
        dao.addMovie(this.movieDetailsDb(movieId))
        Log.i("db", "inserted")
    }


    private val popularMoviesFlow = flow {
        val movies = movieApi.getPopularMovies().movies.map { (Movie(id = it.id, title = it.title, image = "https://image.tmdb.org/t/p/w500${it.posterPath}")) }
        emit(movies)
    }


    override fun popularMovies(): Flow<List<Movie>> = popularMoviesFlow


    private val nowPlayingMoviesFlow = flow {
        val movies = movieApi.getNowPlayingMovies().movies.map { (Movie(id = it.id, title = it.title, image = "https://image.tmdb.org/t/p/w500${it.posterPath}")) }
        emit(movies)
    }

    override fun nowPlaying(): Flow<List<Movie>> = nowPlayingMoviesFlow

    private val upcomingMoviesFlow = flow {
        val movies = movieApi.getUpcomingMovies().movies.map { (Movie(id = it.id, title = it.title, image = "https://image.tmdb.org/t/p/w500${it.posterPath}")) }
        emit(movies)
    }

    override fun upcoming(): Flow<List<Movie>> = upcomingMoviesFlow

    private val topRatedMoviesFlow = flow {
        val movies = movieApi.getTopRatedMovies().movies.map { (Movie(id = it.id, title = it.title, image = "https://image.tmdb.org/t/p/w500${it.posterPath}")) }
        emit(movies)
    }

    override fun topRated(): Flow<List<Movie>> = topRatedMoviesFlow

    override fun favoriteMovies(): Flow<List<DbMovie>> = dao.getAll()

        /*
        flow {
            val favorite = movieDatabase.getFavoriteMovies().toList()
            emit(favorite)
        }
        */


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


    override suspend fun updateFavorites(movieId: Int) {
        if(dao.delete(movieDetailsDb(movieId))==0) {
            insertMovieToDatabase(movieId)
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

