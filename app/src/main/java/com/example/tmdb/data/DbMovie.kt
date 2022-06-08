package com.example.tmdb.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class DbMovie (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    val title: String,
    val releaseDate: String,
    val vote: Float,
    val runtime: Int,
    val overview: String
)