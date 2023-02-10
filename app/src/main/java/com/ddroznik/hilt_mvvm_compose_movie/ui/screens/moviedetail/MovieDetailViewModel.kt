package com.ddroznik.hilt_mvvm_compose_movie.ui.screens.moviedetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddroznik.hilt_mvvm_compose_movie.data.model.BaseModel
import com.ddroznik.movies_test.data.model.artist.Artist
import com.ddroznik.hilt_mvvm_compose_movie.data.model.moviedetail.MovieDetail
import com.ddroznik.movies_test.data.repository.MovieRepository
import com.ddroznik.movies_test.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repo: MovieRepository) : ViewModel() {
    val movieDetail: MutableState<DataState<MovieDetail>?> = mutableStateOf(null)
    val recommendedMovie: MutableState<DataState<BaseModel>?> = mutableStateOf(null)
    val artist: MutableState<DataState<Artist>?> = mutableStateOf(null)

    fun movieDetailApi(movieId: Int) {
        viewModelScope.launch {
            repo.movieDetail(movieId).onEach {
                movieDetail.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun recommendedMovieApi(movieId: Int, page: Int) {
        viewModelScope.launch {
            repo.recommendedMovie(movieId, page).onEach {
                recommendedMovie.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun movieCredit(movieId: Int) {
        viewModelScope.launch {
            repo.movieCredit(movieId).onEach {
                artist.value = it
            }.launchIn(viewModelScope)
        }
    }

}