package com.example.tmdb.viewModels

import androidx.lifecycle.ViewModel
import com.example.tmdb.MovieRep
import com.example.tmdb.rep.Movie


class HomeViewModel(private val rep : MovieRep):ViewModel() {
    val filmovi = rep.filmovi
    val favorite = rep.favouriteMovies()

    fun setDetails(movie:Movie){
        rep.updateDetails(movie)
    }

    fun addFavorite(movie:Movie){
        rep.addFavorite(movie)
    }

    fun removeFavorite(movie:Movie){
        rep.removeFavorite(movie)
    }


    fun getPopular():List<Movie>{
        var popular:MutableList<Movie> = mutableListOf()
        for (film in filmovi){
            if(film.popular){
                popular.add(film)
            }
        }
        return popular

    }

    fun getFree():List<Movie>{
        var free:MutableList<Movie> = mutableListOf()
        for (film in filmovi){
            if(film.free){
                free.add(film)
            }
        }
        return free

    }

    fun getTrending():List<Movie>{
        var trending:MutableList<Movie> = mutableListOf()
        for (film in filmovi){
            if(film.trending){
                trending.add(film)
            }
        }
        return trending

    }

}