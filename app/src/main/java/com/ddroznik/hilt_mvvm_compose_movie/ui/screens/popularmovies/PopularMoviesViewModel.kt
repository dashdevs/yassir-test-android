package com.ddroznik.hilt_mvvm_compose_movie.ui.screens.popularmovies


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ddroznik.hilt_mvvm_compose_movie.data.repository.MovieRepository
import com.ddroznik.hilt_mvvm_compose_movie.domain.usecase.GetListOfPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(getListOfPopularMoviesUseCase: GetListOfPopularMoviesUseCase) : ViewModel() {
    val popularMovies = getListOfPopularMoviesUseCase.invoke().cachedIn(viewModelScope)
}