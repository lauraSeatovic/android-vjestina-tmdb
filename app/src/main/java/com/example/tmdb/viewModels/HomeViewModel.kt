package com.example.tmdb.viewModels

import androidx.lifecycle.ViewModel
import com.example.tmdb.repository.Movie
import com.example.tmdb.repository.MovieRepository
import com.example.tmdb.repository.MovieRepositoryImpl
import com.example.tmdb.repository.listOfMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class HomeViewModel(private val movieRepository: MovieRepositoryImpl) : ViewModel() {

    fun getPopular(): Flow<List<Movie>> = movieRepository.popularMovies()
    fun getFree(): Flow<List<Movie>> = movieRepository.freeMovies()
    fun getTrending(): Flow<List<Movie>> = movieRepository.trendingMovies()

    private var _viewState = MutableStateFlow<List<Movie>>(emptyList())
    val viewState: StateFlow<List<Movie>> = _viewState.asStateFlow()

    fun fetchFavorite() {
        _viewState.value = movieRepository.favoriteMovies()
    }

    fun updateFavorite(movieId: Int) {
        movieRepository.updateFavorites(movieId)
        _viewState.value = movieRepository.favoriteMovies()
    }
}