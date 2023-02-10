package com.ddroznik.hilt_mvvm_compose_movie.ui.screens.popularmovies


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ddroznik.hilt_mvvm_compose_movie.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(repo: MovieRepository) : ViewModel() {
    val popularMovies = repo.popularPagingDataSource().cachedIn(viewModelScope)
}