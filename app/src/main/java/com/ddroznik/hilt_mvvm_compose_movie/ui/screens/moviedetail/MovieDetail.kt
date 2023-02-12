package com.ddroznik.hilt_mvvm_compose_movie.ui.screens.moviedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ddroznik.hilt_mvvm_compose_movie.R
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.ApiURL
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.DataState
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.moviedetail.MovieDetail
import com.ddroznik.hilt_mvvm_compose_movie.ui.component.CircularIndeterminateProgressBar
import com.ddroznik.hilt_mvvm_compose_movie.ui.component.text.SubtitlePrimary
import com.ddroznik.hilt_mvvm_compose_movie.ui.component.text.SubtitleSecondary
import com.ddroznik.hilt_mvvm_compose_movie.utils.hourMinutes
import com.ddroznik.hilt_mvvm_compose_movie.utils.pagingLoadingState
import com.ddroznik.hilt_mvvm_compose_movie.ui.theme.DefaultBackgroundColor
import com.ddroznik.hilt_mvvm_compose_movie.ui.theme.FontColor
import com.ddroznik.hilt_mvvm_compose_movie.ui.theme.SecondaryFontColor

@Composable
fun MovieDetail(navController: NavController, movieId: Int) {
    val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()
    val progressBar = remember { mutableStateOf(false) }
    val movieDetail = movieDetailViewModel.movieDetail

    LaunchedEffect(true) {
        movieDetailViewModel.getMovieDetails(movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                DefaultBackgroundColor
            )
    ) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)
        movieDetail.value?.let { it ->
            if (it is DataState.Success<MovieDetail>) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Image(
                        painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(it.data.poster_path)),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 15.dp, end = 15.dp)
                    ) {
                        Text(
                            text = it.data.title,
                            modifier = Modifier.padding(top = 10.dp),
                            color = FontColor,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.W700,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp, top = 10.dp)
                        ) {

                            Column(Modifier.weight(1f)) {
                                SubtitlePrimary(
                                    text = it.data.original_language,
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.language)
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                SubtitlePrimary(
                                    text = it.data.vote_average.toString(),
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.rating)
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                SubtitlePrimary(
                                    text = it.data.runtime.hourMinutes()
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.duration)
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                SubtitlePrimary(
                                    text = it.data.release_date
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.release_date)
                                )
                            }
                        }
                        Text(
                            text = stringResource(R.string.description),
                            color = FontColor,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = it.data.overview,
                            color = SecondaryFontColor,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                }
            }
        }
        movieDetail.pagingLoadingState {
            progressBar.value = it
        }
    }
}

@Preview(name = "MovieDetail", showBackground = true)
@Composable
fun Preview() {
    // MovieDetail(null, MovieItem())
}
