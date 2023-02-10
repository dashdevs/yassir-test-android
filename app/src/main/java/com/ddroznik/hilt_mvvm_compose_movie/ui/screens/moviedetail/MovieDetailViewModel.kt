package com.ddroznik.hilt_mvvm_compose_movie.ui.screens.moviedetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.moviedetail.MovieDetail
import com.ddroznik.hilt_mvvm_compose_movie.domain.usecase.GetMovieDetailsUseCase
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) : ViewModel() {
    val movieDetail: MutableState<DataState<MovieDetail>?> = mutableStateOf(null)

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId).onEach {
                movieDetail.value = it
            }.launchIn(viewModelScope)
        }
    }

}