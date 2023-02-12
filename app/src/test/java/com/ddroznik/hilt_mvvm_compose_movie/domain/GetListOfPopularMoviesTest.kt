package com.ddroznik.hilt_mvvm_compose_movie.domain

import androidx.paging.map
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.MovieItem
import com.ddroznik.hilt_mvvm_compose_movie.domain.usecase.GetListOfPopularMoviesUseCase
import com.ddroznik.hilt_mvvm_compose_movie.repository.FakeRepository
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class GetListOfPopularMoviesUseCaseTest {
    private lateinit var fakeRepository: FakeRepository
    private lateinit var getListOfPopularMoviesUseCase : GetListOfPopularMoviesUseCase
        private val moviesToInsert = mutableListOf<MovieItem>()
        @Before
        fun setup() {
            fakeRepository = FakeRepository()
            getListOfPopularMoviesUseCase = GetListOfPopularMoviesUseCase(fakeRepository)
            val movieItem = MovieItem()
            val movieItem2 = MovieItem()
            movieItem.id = 1
            movieItem2.id = 2
            moviesToInsert.add(movieItem)
            moviesToInsert.add(movieItem2)
            runBlocking { moviesToInsert.forEach{fakeRepository.addMovie(it)} }
        }

        @Test
        fun getListOfPopularMoviesUseCaseTest() = runBlocking {
            getListOfPopularMoviesUseCase.invoke().collect {it.map {
                assert(it == moviesToInsert[0] || it == moviesToInsert[1])
                  assertThat(it, instanceOf(MovieItem::class.java))
            }}
            }

}




