package com.ddroznik.hilt_mvvm_compose_movie.repository

import androidx.paging.PagingData
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.DataState
import com.ddroznik.hilt_mvvm_compose_movie.data.repository.IMovieRepository
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.MovieItem
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.moviedetail.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : IMovieRepository {
    private var movies = mutableListOf<MovieItem>()
    private var movieDetails = mutableListOf<MovieDetail>()

    override fun popularPagingDataSource(): Flow<PagingData<MovieItem>> {
        return flow { emit(PagingData.from(movies)) }
    }

    override suspend fun movieDetail(id: Int): Flow<DataState<MovieDetail>> {
        movieDetails.find { it.id == id }?.let {
            return flow { emit(DataState.Success(it)) }
        }
        return flow { emit(DataState.Error(Exception("Movie not found"))) }
    }
    suspend fun addMovie(movie: MovieItem) {
        movies.add(movie)
    }
    suspend fun addMovieDetail(movieDetail: MovieDetail) {
        movieDetails.add(movieDetail)
    }

}

