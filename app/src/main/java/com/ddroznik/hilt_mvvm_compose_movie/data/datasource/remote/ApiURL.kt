package com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote

object ApiURL {
    private const val API_KEY = "c9856d0cb57c3f14bf75bdc6c063b8f3"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w342"
    const val POPULAR_MOVIE_LIST = "movie/popular?api_key=$API_KEY&language=en-US"
    const val MOVIE_DETAIL = "movie/{movieId}?api_key=$API_KEY&language=en-US"
}