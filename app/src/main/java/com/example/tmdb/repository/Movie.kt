package com.example.tmdb.repository

import android.text.BoringLayout
import com.example.tmdb.R
import com.example.tmdb.data.TopCastData
import com.example.tmdb.data.WritersData


data class Movie(
    val id: Int = -1,
    val image: Int =-1,
    val title: String ="",
    val year: Int =-1,
    val score: Int = -1,
    val date: String ="",
    val duration: String ="",
    val country: String = "",
    val genre: String = "",
    val description: String = "",
    val authors: List<WritersData> = listOf(),
    val topCast: List<TopCastData> = listOf(),
    val popular: Boolean = false,
    val free: Boolean = false,
    val trending: Boolean = false

)

val listOfMovies = listOf(
    Movie(
        id =1,
        image = R.drawable.ironman,
        title = "Iron Man",
        year = 2008,
        score = 76,
        date = "02/08/2016",
        duration ="2h 30",
        country = "US",
        genre = "Action",
        description = "Fun movie",
        authors = listOf(WritersData("john", "sreenplay")),
        topCast = listOf(TopCastData(R.drawable.robert, "robert", "Iron man" )),
        popular = false,
        free = false,
        trending = true

    ),
    Movie(
        id =2,
        image = R.drawable.gattaca,
        title = "Gattaca",
        year = 2010,
        score = 15,
        date = "02/08/2016",
        duration ="1h 30",
        country = "US",
        genre = "Action",
        description = "Action movie",
        authors = listOf(WritersData("john", "sreenplay"), WritersData("david", "sreenplay")),
        topCast = listOf(TopCastData(R.drawable.terrence, "terrence", "man" )),
        popular = false,
        free = false,
        trending = true

    ),
    Movie(
        id =3,
        image = R.drawable.ironman2,
        title = "Iron Man 2",
        year = 2016,
        score = 85,
        duration ="2h 15",
        date = "02/08/2020",
        country = "US",
        genre = "Action, Comedy",
        description = "Fun movie",
        authors = listOf(WritersData("john", "sreenplay"), WritersData("john2", "sreenplay")),
        topCast = listOf(TopCastData(R.drawable.robert, "robert", "Iron man" ), TopCastData(R.drawable.robert, "robert", "robert" )),
        popular = true,
        free = true,
        trending = true

    ),
    Movie(
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
)
