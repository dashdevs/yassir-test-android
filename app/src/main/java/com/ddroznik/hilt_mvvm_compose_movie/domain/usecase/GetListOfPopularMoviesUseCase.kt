package com.ddroznik.hilt_mvvm_compose_movie.domain.usecase

import androidx.paging.PagingData
import com.ddroznik.hilt_mvvm_compose_movie.data.repository.IMovieRepository
import com.ddroznik.hilt_mvvm_compose_movie.data.repository.MovieRepository
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListOfPopularMoviesUseCase @Inject constructor(private val repo: IMovieRepository) {
    operator fun invoke(): Flow<PagingData<MovieItem>> {
        return repo.popularPagingDataSource()
    }
}