package com.example.tmdb.modules


import com.example.tmdb.MovieDatabase
import com.example.tmdb.MovieRep
import com.example.tmdb.viewModels.DetailsViewModel
import com.example.tmdb.viewModels.FavoriteViewModel
import com.example.tmdb.viewModels.HomeViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel



val appModule = module {
    single { MovieDatabase() }
    single { MovieRep(get()) }
    // ViewModel for Detail View
    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }

}