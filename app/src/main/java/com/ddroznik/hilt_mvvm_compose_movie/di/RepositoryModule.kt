package com.ddroznik.hilt_mvvm_compose_movie.di

import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.local.MovieDB
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.ApiService
import com.ddroznik.hilt_mvvm_compose_movie.data.repository.IMovieRepository
import com.ddroznik.hilt_mvvm_compose_movie.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(
        apiService: ApiService,
        db: MovieDB
    ): IMovieRepository {
        return MovieRepository(
            apiService, db
        )
    }

}