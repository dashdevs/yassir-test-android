package com.ddroznik.hilt_mvvm_compose_movie.ui.component.appbar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import com.ddroznik.movies_test.ui.theme.Purple500

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
