package com.example.tmdb

import android.util.Log
import com.example.tmdb.rep.Movie
import com.example.tmdb.rep.TopCastData
import com.example.tmdb.rep.WritersData
import java.io.Console

class MovieDatabase {
    val movie1 = Movie(
    id =4,
    image = R.drawable.puppylove,
    title = "Puppy love",
    year = 2008,
    score = 55,
    date = "02/08/2016",
    duration ="2h 11",
    country = "US",
    genre = "RomCom",
    description = "Fun movie",
    authors = listOf(WritersData("john", "sreenplay")),
    topCast = listOf(TopCastData(R.drawable.robert, "robert", "Iron man" )),
    popular = true,
    free = false,
    trending = false

    )

    val favoriti: MutableList<Movie> = ArrayList()
    fun getFavoriteMovies():MutableList<Movie>{
        return favoriti
    }

    fun saveIsMovieFavourite(movie:Movie, isFav: Boolean){
        Log.i("mesmsms", movie.title)
    }

    fun addFavorite(movie:Movie) {
        if (!favoriti.contains(movie)) {
            favoriti.add(movie)
        }
    }

    fun removeFavorite(movie:Movie) {
        if (favoriti.contains(movie)) {
            favoriti.remove(movie)
        }
    }

    init{
        addFavorite(movie1)
    }
}