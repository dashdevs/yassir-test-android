package com.ddroznik.hilt_mvvm_compose_movie.data.local

import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.local.MovieDB
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.local.MovieDao
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.MovieItem
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class MovieTest {
    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)
    private lateinit var dao: MovieDao
    @Inject
    @Named("test_db")
    lateinit var db: MovieDB
    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        dao = db.movieDao
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertMovieItemAndGetFromDataBaseTest() = runBlocking {
        val movie = MovieItem()
        dao.insert(movie)
        val allMovies = dao.getAllMovies()
        assertThat(allMovies, instanceOf(List::class.java))
        assertThat(allMovies[0], instanceOf(MovieItem::class.java))
        assert(allMovies[0].id == movie.id)
    }

    @Test
    fun insertAllMovieItemsAndGetFromDataBaseTest() = runBlocking {
        val movie = MovieItem()
        val movieDos = MovieItem()
        movieDos.id = 2
        val listOfMovies = listOf(movie, movieDos)
        dao.insertAll(listOfMovies)
        val allMovies = dao.getAllMovies()
        assertThat(allMovies, instanceOf(List::class.java))
        assertThat(allMovies[0], instanceOf(MovieItem::class.java))
        assert(allMovies[0].id == movie.id)
        assert(allMovies.size == listOfMovies.size)

    }

    @Test
    fun insertMovieDetailAndGetFromDataBaseTest() = runBlocking {
        val movie = MovieItem()
        movie.id = 1
        movie.title = "title"
        movie.overview = "overview"
        movie.posterPath = "posterPath"
        movie.backdropPath = "backdropPath"
        movie.releaseDate = "releaseDate"
        movie.voteAverage = 1.0
        movie.voteCount = 1
        dao.insert(movie)
        val allMovies = dao.getAllMovies()
        assertThat(allMovies, instanceOf(List::class.java))
        assertThat(allMovies[0], instanceOf(MovieItem::class.java))
        assert(allMovies[0].id == movie.id)
        assert(allMovies[0].title == movie.title)
        assert(allMovies[0].overview == movie.overview)
        assert(allMovies[0].posterPath == movie.posterPath)
        assert(allMovies[0].backdropPath == movie.backdropPath)
        assert(allMovies[0].releaseDate == movie.releaseDate)
        assert(allMovies[0].voteAverage == movie.voteAverage)
        assert(allMovies[0].voteCount == movie.voteCount)
    }


}