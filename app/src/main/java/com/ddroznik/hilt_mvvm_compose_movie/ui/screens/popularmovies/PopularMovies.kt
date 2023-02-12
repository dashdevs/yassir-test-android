package com.ddroznik.hilt_mvvm_compose_movie.ui.screens.popularmovies

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ddroznik.hilt_mvvm_compose_movie.ui.component.MovieListScreen

@Composable
fun NowPlaying(
    navController: NavController,
) {
    val nowPlayViewModel = hiltViewModel<PopularMoviesViewModel>()
    MovieListScreen(
        navController = navController,
        movies = nowPlayViewModel.popularMovies
    )
}