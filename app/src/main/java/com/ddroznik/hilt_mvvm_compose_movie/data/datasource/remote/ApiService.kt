package com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote

import com.ddroznik.hilt_mvvm_compose_movie.domain.model.BaseModel
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.moviedetail.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ApiURL.POPULAR_MOVIE_LIST)
    suspend fun popularMovieList(@Query("page") page: Int): BaseModel

    @GET(ApiURL.MOVIE_DETAIL)
    suspend fun movieDetail(@Path("movieId") movieId: Int): MovieDetail

}