package com.example.tmdb.api

interface MovieApi {
    suspend fun getPopularMovies(): MovieListResponse
    suspend fun getNowPlayingMovies(): MovieListResponse
    suspend fun getUpcomingMovies(): MovieListResponse
    suspend fun getTopRatedMovies(): MovieListResponse
    suspend fun getCredits(movieId: Int): CastListResponse
    suspend fun getDetails(movieId: Int): MovieDetailsResponse
    suspend fun search(input: String): MovieListResponse
}