package com.example.tmdb.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.api.MovieListResponse
import com.example.tmdb.api.MovieResponse
import com.example.tmdb.repository.Movie
import com.example.tmdb.repository.MovieRepository
import com.example.tmdb.repository.MovieRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeViewModel(private val movieRepository: MovieRepositoryImpl) : ViewModel() {

    fun getPopular(): Flow<List<Movie>> = movieRepository.popularMovies()
    fun getNowPlaying(): Flow<List<Movie>> = movieRepository.nowPlaying()
    fun getTopRated(): Flow<List<Movie>> = movieRepository.topRated()
    fun getUpcoming(): Flow<List<Movie>> = movieRepository.upcoming()

    private var _viewState = MutableStateFlow<List<Movie>>(emptyList())
    val viewState: StateFlow<List<Movie>> = _viewState.asStateFlow()

    fun fetchFavorite() {
        viewModelScope.launch {
            movieRepository.favoriteMovies()
                .collect { favoriteMovies ->
                    _viewState.value = favoriteMovies.map { Movie(it.id, it.image, it.title, it.overview) }
                }
        }
    }

    fun updateFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.updateFavorites(movieId)
            movieRepository.favoriteMovies()
                .collect { favoriteMovies ->
                    _viewState.value = favoriteMovies.map { Movie(it.id, it.image, it.title, it.overview) }
                }
        }
    }
}