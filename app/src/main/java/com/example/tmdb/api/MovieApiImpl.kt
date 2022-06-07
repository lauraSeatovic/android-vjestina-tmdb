package com.example.tmdb.api

import com.example.tmdb.repository.Movie
import io.ktor.client.*
import io.ktor.client.request.*


private val API_KEY = "f71d703314a5e4882975e501430106cf"

class MovieApiImpl(
    private val client: HttpClient
) : MovieApi {
    override suspend fun getPopularMovies(): MovieListResponse =
        client.get("https://api.themoviedb.org/3/movie/popular?api_key=$API_KEY")

    override suspend fun getNowPlayingMovies(): MovieListResponse =
        client.get("https://api.themoviedb.org/3/movie/now_playing?api_key=$API_KEY")

    override suspend fun getUpcomingMovies(): MovieListResponse =
        client.get("https://api.themoviedb.org/3/movie/upcoming?api_key=$API_KEY")

    override suspend fun getTopRatedMovies(): MovieListResponse =
        client.get("https://api.themoviedb.org/3/movie/top_rated?api_key=$API_KEY")

    override suspend fun getCredits(movieId: Int): CastListResponse =
        client.get("https://api.themoviedb.org/3/movie/$movieId/credits?api_key=$API_KEY")

    override suspend fun getDetails(movieId: Int): MovieDetailsResponse =
        client.get("https://api.themoviedb.org/3/movie/$movieId?api_key=$API_KEY&language=en-US")

    override suspend fun search(input: String): MovieListResponse =
        client.get("https://api.themoviedb.org/3/search/movie?api_key=$API_KEY&query=${input.replace(' ', '+')}")
}