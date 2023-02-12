package com.ddroznik.hilt_mvvm_compose_movie.di

import android.content.Context
import androidx.room.Room
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.local.MovieDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object AppModuleTest {

    @Provides
    @Named("test_db")
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, MovieDB::class.java).build()
}