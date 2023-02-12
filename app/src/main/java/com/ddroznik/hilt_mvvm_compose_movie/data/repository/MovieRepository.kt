package com.ddroznik.hilt_mvvm_compose_movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.PopularPagingDataSource
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.local.MovieDB
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.ApiService
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.DataState
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.moviedetail.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val db: MovieDB
) : IMovieRepository {
    override suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.movieDetail(movieId)
            db.movieDao.insert(searchResult)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            try {
                val searchResult = db.movieDao.getMovieDetail(movieId)
                if (searchResult != null) {
                    emit(DataState.Success(searchResult))
                } else {
                    emit(DataState.Error(e))
                }
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }

    override fun popularPagingDataSource() = Pager(
        pagingSourceFactory = { PopularPagingDataSource(apiService, db) },
        config = PagingConfig(pageSize = 1)
    ).flow

}