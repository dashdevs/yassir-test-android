package com.ddroznik.hilt_mvvm_compose_movie.navigation

import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import com.ddroznik.hilt_mvvm_compose_movie.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.app_title,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Home : Screen("home_screen")
    object NavigationDrawer :
        Screen("navigation_drawer", objectName = "genreId", objectPath = "/{genreId}")

    object MovieDetail :
        Screen("movie_detail_screen", objectName = "movieItem", objectPath = "/{movieItem}")

}