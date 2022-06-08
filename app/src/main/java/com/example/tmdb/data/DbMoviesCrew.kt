package com.example.tmdb.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["movieId", "name"])
data class DbMoviesCrew(
    val movieId: String,
    val name: String,
    val image: String,
    val job: String
)

data class MoviesWithCrew(
    @Embedded val movie: DbMovie,
    @Relation(
        parentColumn = "id",
        entityColumn = "name",
        associateBy = Junction(DbMoviesCrew::class)
    )
    val cast: List<DbCrew>
)
