package com.example.tmdb.viewModels

import androidx.lifecycle.ViewModel
import com.example.tmdb.MovieRep
import com.example.tmdb.rep.Movie

class DetailsViewModel(private val rep : MovieRep): ViewModel() {
    fun getDetails():Movie{
        return rep.updateDetails()
    }
}