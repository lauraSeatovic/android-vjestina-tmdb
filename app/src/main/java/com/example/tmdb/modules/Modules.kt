package com.example.tmdb.modules


import androidx.room.Room
import com.example.tmdb.api.MovieApiImpl
import com.example.tmdb.data.AppDatabase
import com.example.tmdb.repository.MovieRepositoryImpl
import com.example.tmdb.viewModels.DetailsViewModel
import com.example.tmdb.viewModels.FavoriteViewModel
import com.example.tmdb.viewModels.HomeViewModel
import com.example.tmdb.viewModels.SearchViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app-database").allowMainThreadQueries().fallbackToDestructiveMigration().build() }
    single {
        HttpClient(Android) {
            // Logging
            install(Logging) {
                level = LogLevel.ALL
            }
            // JSON
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
    single { MovieApiImpl(get()) }
    single { MovieRepositoryImpl(get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { SearchViewModel(get()) }

}