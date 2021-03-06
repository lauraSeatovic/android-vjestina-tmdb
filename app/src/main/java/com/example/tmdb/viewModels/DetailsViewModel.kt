package com.example.tmdb.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tmdb.repository.Movie
import com.example.tmdb.repository.MovieDetails
import com.example.tmdb.repository.MovieRepository
import com.example.tmdb.repository.MovieRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapLatest

class DetailsViewModel(private val movieRepository: MovieRepositoryImpl) : ViewModel() {
    fun showMovie(movieId: Int): Flow<MovieDetails> {
        return movieRepository.movieDetails(movieId)
    }
}