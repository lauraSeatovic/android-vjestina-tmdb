package com.example.tmdb.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.MovieRep
import com.example.tmdb.rep.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.random.Random

data class FavoriteViewState(
    val favorite: List<Movie> = emptyList()
)

class FavoriteViewModel(private val rep : MovieRep): ViewModel() {
    val favoriteList: List<Movie> = emptyList()

    val state = FavoriteViewState()

    private var _viewState = MutableStateFlow(FavoriteViewState())
    val viewState: StateFlow<FavoriteViewState> = _viewState
    //val viewState = _viewState.asStateFlow()

    private fun fetchFavorite(){
        val favorite = rep.favouriteMovies()
        _viewState.value = _viewState.value.copy(favorite = favorite)
    }

    init{
        fetchFavorite()
    }

    val favorite = rep.favouriteMovies()
    val favorite2 = rep.favouriteMovies2()

    fun addFavorite(movie:Movie){
        rep.addFavorite(movie)

    }

    fun setDetails(movie:Movie){
        rep.updateDetails(movie)
    }

    fun removeFavorite(movie:Movie){
        rep.removeFavorite(movie)
        viewModelScope.launch {
            _viewState.emit(state.copy(favorite=rep.favouriteMovies()))
        }
        Log.i("remove", _viewState.value.toString())

    }



    private val _favoriteMovies = MutableStateFlow(listOf<Movie>())
    val favoriteMovies = _favoriteMovies.asStateFlow()

    private fun getFavoriteMovies(){
        viewModelScope.launch(Dispatchers.Default){

        }
    }



}