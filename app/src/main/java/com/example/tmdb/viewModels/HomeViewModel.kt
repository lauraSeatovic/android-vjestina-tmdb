package com.example.tmdb.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.repository.Movie
import com.example.tmdb.repository.MovieRepository
import com.example.tmdb.repository.MovieRepositoryImpl
import com.example.tmdb.repository.listOfMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeViewModel(private val movieRepository: MovieRepositoryImpl) : ViewModel() {

    fun getPopular(): Flow<List<Movie>> = movieRepository.popularMovies()
    fun getFree(): Flow<List<Movie>> = movieRepository.freeMovies()
    fun getTrending(): Flow<List<Movie>> = movieRepository.trendingMovies()

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