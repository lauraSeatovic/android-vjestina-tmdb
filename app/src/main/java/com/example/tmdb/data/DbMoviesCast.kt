package com.example.tmdb.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.tmdb.repository.Movie


@Entity(primaryKeys = ["movieId", "name"])
data class DbMoviesCast(
    val movieId: String,
    val name: String,
    val image: String,
    val role: String
)

data class MoviesWithCast(
    @Embedded val movie: DbMovie,
    @Relation(
        parentColumn = "id",
        entityColumn = "name",
        associateBy = Junction(DbMoviesCast::class)
    )
    val cast: List<DbCast>
)
