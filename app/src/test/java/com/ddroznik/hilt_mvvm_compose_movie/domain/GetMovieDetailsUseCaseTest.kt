package com.ddroznik.hilt_mvvm_compose_movie.domain

import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.DataState
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.moviedetail.MovieDetail
import com.ddroznik.hilt_mvvm_compose_movie.domain.usecase.GetMovieDetailsUseCase
import com.ddroznik.hilt_mvvm_compose_movie.repository.FakeRepository
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class GetMovieDetailsUseCaseTest {

    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    private lateinit var fakeRepository: FakeRepository
    private val moviesToInsert = mutableListOf<MovieDetail>()
    @Before
    fun setup() {
        fakeRepository = FakeRepository()
        getMovieDetailsUseCase = GetMovieDetailsUseCase(fakeRepository)
        val movieDetail = MovieDetail()
        val movieDetail2 = MovieDetail()
        movieDetail.id = 1
        movieDetail2.id = 2
        moviesToInsert.add(movieDetail)
        runBlocking { moviesToInsert.forEach{fakeRepository.addMovieDetail(it)} }
    }

    @Test
    fun shouldGetMovieDetailsForId() = runBlocking {
        getMovieDetailsUseCase.invoke(1).collect {
            assertThat(it, instanceOf(DataState.Success::class.java))
            assertThat((it as DataState.Success).data, instanceOf(MovieDetail::class.java))
            assert(it.data == moviesToInsert[0])
        }
    }

    @Test
    fun shouldFailToFindMovieDetailsAndThrowError() = runBlocking {
        getMovieDetailsUseCase.invoke(3).collect {
            assertThat(it, instanceOf(DataState.Error::class.java))
        }
    }


}