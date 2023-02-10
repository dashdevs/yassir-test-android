package com.ddroznik.hilt_mvvm_compose_movie.ui.component.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ddroznik.movies_test.ui.theme.subTitleSecondary

@Composable
fun SubtitleSecondary(text:String) {
    Text(
        text = text,
        style = MaterialTheme.typography.subTitleSecondary
    )
}