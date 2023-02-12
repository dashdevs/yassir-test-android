package com.ddroznik.hilt_mvvm_compose_movie.data.repository

import androidx.paging.PagingData
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.DataState
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.MovieItem
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.moviedetail.MovieDetail
import kotlinx.coroutines.flow.Flow

interface IMovieRepository  {
    fun popularPagingDataSource(): Flow<PagingData<MovieItem>>
    suspend fun movieDetail(id: Int): Flow<DataState<MovieDetail>>
}