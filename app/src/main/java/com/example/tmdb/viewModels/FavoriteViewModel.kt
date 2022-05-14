package com.example.tmdb.viewModels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.repository.Movie
import com.example.tmdb.repository.MovieRepository
import com.example.tmdb.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class FavoriteViewModel(private val movieRepository: MovieRepositoryImpl) : ViewModel() {


    private var _viewState = MutableStateFlow<List<Movie>>(emptyList())
    val viewState: StateFlow<List<Movie>> = _viewState.asStateFlow()

    fun fetchFavorite() {
        viewModelScope.launch {
            movieRepository.favoriteMovies()
                .collect { favoriteMovies ->
                    _viewState.value = favoriteMovies
                }
        }
    }

    fun updateFavorite(movieId: Int) {
        movieRepository.updateFavorites(movieId)
        viewModelScope.launch {
            movieRepository.favoriteMovies()
                .collect { favoriteMovies ->
                    _viewState.value = favoriteMovies
                }
        }
    }
}
