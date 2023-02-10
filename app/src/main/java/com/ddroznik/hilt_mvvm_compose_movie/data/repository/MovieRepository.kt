package com.ddroznik.hilt_mvvm_compose_movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.ApiService
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.paging.PopularPagingDataSource
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.moviedetail.MovieDetail
import com.ddroznik.movies_test.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.movieDetail(movieId)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    fun popularPagingDataSource() = Pager(
        pagingSourceFactory = { PopularPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

}