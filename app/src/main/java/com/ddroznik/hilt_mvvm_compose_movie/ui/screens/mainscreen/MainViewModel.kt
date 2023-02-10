package com.ddroznik.hilt_mvvm_compose_movie.ui.screens.mainscreen

import androidx.lifecycle.ViewModel
import com.ddroznik.hilt_mvvm_compose_movie.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: MovieRepository) : ViewModel() {


}