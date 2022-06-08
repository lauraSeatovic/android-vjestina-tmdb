package com.example.tmdb.data

import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie: DbMovie)

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<DbMovie>>

    @Delete
    fun delete(movie: DbMovie):Int

    @Query("SELECT * FROM movie")
    fun getMoviesAsList(): List<DbMovie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMoviesCast(crossRef: DbMoviesCast)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCast(cast: DbCast)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMoviesCrew(crossRef: DbMoviesCrew)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCrew(cast: DbCrew)

    @Transaction
    @Query("SELECT * FROM DbMoviesCast WHERE movieId = :id")
    suspend fun getMovieCast(id: String): List<DbMoviesCast>

    @Transaction
    @Query("SELECT * FROM DbMoviesCrew WHERE movieId = :id")
    suspend fun getMovieCrew(id: String): List<DbMoviesCrew>

    @Transaction
    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovie(id: String): DbMovie
}