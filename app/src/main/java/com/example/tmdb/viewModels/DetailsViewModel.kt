package com.example.tmdb.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.repository.Movie
import com.example.tmdb.repository.MovieDetails
import com.example.tmdb.repository.MovieRepository
import com.example.tmdb.repository.MovieRepositoryImpl
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.compose.viewModel
import java.lang.IllegalArgumentException
import java.lang.NullPointerException

class DetailsViewModel(private val movieRepository: MovieRepositoryImpl) : ViewModel() {


    fun showMovieDb(movieId: Int): Flow<MovieDetails?>{
        return movieRepository.getMovieFromDb(movieId)
    }

    fun showMovie(movieId: Int): Flow<MovieDetails?>{
        return movieRepository.movieDetails(movieId)
    }
}