package com.ddroznik.hilt_mvvm_compose_movie.ui.component.appbar

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.ddroznik.hilt_mvvm_compose_movie.ui.theme.Purple500

@Composable
fun HomeAppBar(title: String) {
    TopAppBar(
        backgroundColor = Purple500,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
            )
        }
    )
}
