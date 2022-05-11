package com.example.tmdb.modules


import com.example.tmdb.repository.MovieDatabaseImpl
import com.example.tmdb.repository.MovieRepositoryImpl
import com.example.tmdb.viewModels.DetailsViewModel
import com.example.tmdb.viewModels.FavoriteViewModel
import com.example.tmdb.viewModels.HomeViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel



val appModule = module {
    single { MovieDatabaseImpl() }
    single { MovieRepositoryImpl(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }

}