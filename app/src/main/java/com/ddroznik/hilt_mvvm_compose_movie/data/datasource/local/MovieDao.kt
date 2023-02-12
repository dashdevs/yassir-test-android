package com.ddroznik.hilt_mvvm_compose_movie.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.MovieItem
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.moviedetail.MovieDetail

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movie: List<MovieItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieDetail)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieItem>

    @Query("SELECT * FROM movie_detail WHERE id = :movieId")
    suspend fun getMovieDetail(movieId: Int): MovieDetail


}