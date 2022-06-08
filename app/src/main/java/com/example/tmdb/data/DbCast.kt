package com.example.tmdb.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cast")
data class DbCast(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val image: String,
    val job: String
)
