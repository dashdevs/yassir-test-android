package com.ddroznik.hilt_mvvm_compose_movie.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.MovieItem
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.moviedetail.MovieDetail

@Database(entities = [MovieItem::class, MovieDetail::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterMovie::class)
abstract class MovieDB : RoomDatabase() {
    abstract val movieDao: MovieDao

    companion object {
        const val DATABASE_NAME = "Movie_db"
    }
}