package com.ddroznik.hilt_mvvm_compose_movie.domain.usecase

import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.DataState
import com.ddroznik.hilt_mvvm_compose_movie.data.repository.IMovieRepository
import com.ddroznik.hilt_mvvm_compose_movie.data.repository.MovieRepository
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.moviedetail.MovieDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repo: IMovieRepository) {
    suspend operator fun invoke(movieId: Int): Flow<DataState<MovieDetail>> =
        repo.movieDetail(movieId)
}