package com.ddroznik.hilt_mvvm_compose_movie.ui.screens.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ddroznik.hilt_mvvm_compose_movie.R
import com.ddroznik.hilt_mvvm_compose_movie.navigation.Navigation
import com.ddroznik.hilt_mvvm_compose_movie.navigation.Screen
import com.ddroznik.hilt_mvvm_compose_movie.navigation.currentRoute
import com.ddroznik.hilt_mvvm_compose_movie.navigation.navigationTitle
import com.ddroznik.hilt_mvvm_compose_movie.ui.component.CircularIndeterminateProgressBar
import com.ddroznik.hilt_mvvm_compose_movie.ui.component.appbar.AppBarWithArrow
import com.ddroznik.hilt_mvvm_compose_movie.ui.component.appbar.HomeAppBar
import com.ddroznik.hilt_mvvm_compose_movie.utils.networkconnection.ConnectionState
import com.ddroznik.hilt_mvvm_compose_movie.utils.networkconnection.connectivityState


@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val isAppBarVisible = remember { mutableStateOf(true) }
    val searchProgressBar = remember { mutableStateOf(false) }
    // internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available


    Scaffold(scaffoldState = scaffoldState, topBar = {
        when (currentRoute(navController)) {
            Screen.Home.route -> {
                if (isAppBarVisible.value) {
                    HomeAppBar(title = stringResource(R.string.app_title))
                }
            }
            else -> {
                AppBarWithArrow(navigationTitle(navController)) {
                    navController.popBackStack()
                }
            }
        }
    }, snackbarHost = {
        if (isConnected.not()) {
            Snackbar(
                action = {}, modifier = Modifier.padding(8.dp)
            ) {
                Text(text = stringResource(R.string.there_is_no_internet))
            }
        }
    }) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Navigation(navController, Modifier.padding(it))
            Column {
                CircularIndeterminateProgressBar(isDisplayed = searchProgressBar.value, 0.1f)
            }
        }
    }
}