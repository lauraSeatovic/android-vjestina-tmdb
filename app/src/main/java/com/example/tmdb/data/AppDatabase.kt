package com.example.tmdb.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DbMovie::class, DbCast::class, DbCrew::class, DbMoviesCast::class, DbMoviesCrew::class], version =9, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun Dao(): Dao
}