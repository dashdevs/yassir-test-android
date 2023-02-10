package com.ddroznik.hilt_mvvm_compose_movie.ui.screens.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.ddroznik.hilt_mvvm_compose_movie.ui.component.SearchUI
import com.ddroznik.hilt_mvvm_compose_movie.ui.screens.drawer.DrawerUI
import com.ddroznik.hilt_mvvm_compose_movie.utils.networkconnection.ConnectionState
import com.ddroznik.hilt_mvvm_compose_movie.utils.networkconnection.connectivityState
import com.ddroznik.hilt_mvvm_compose_movie.utils.pagingLoadingState
import com.ddroznik.hilt_mvvm_compose_movie.data.model.Genres
import com.ddroznik.movies_test.ui.component.appbar.AppBarWithArrow
import com.ddroznik.movies_test.ui.component.appbar.HomeAppBar
import com.ddroznik.movies_test.ui.component.appbar.SearchBar
import com.ddroznik.movies_test.ui.theme.FloatingActionBackground
import com.ddroznik.movies_test.utils.network.DataState
import kotlinx.coroutines.launch


@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val isAppBarVisible = remember { mutableStateOf(true) }
    val searchProgressBar = remember { mutableStateOf(false) }
    val genreName = remember { mutableStateOf("") }
    // genre list for navigation drawer
    val genres = mainViewModel.genres.value
    // internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    // genre api call for first time
    LaunchedEffect(true) {
        mainViewModel.genreList()
    }

    Scaffold(scaffoldState = scaffoldState, topBar = {
        when (currentRoute(navController)) {
            Screen.Home.route, Screen.NavigationDrawer.route -> {
                if (isAppBarVisible.value) {
                    val appTitle: String =
                        if (currentRoute(navController) == Screen.NavigationDrawer.route) genreName.value
                        else stringResource(R.string.app_title)
                    HomeAppBar(title = appTitle, openDrawer = {
                        scope.launch {
                            scaffoldState.drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }, openFilters = {
                        isAppBarVisible.value = false
                    })
                } else {
                    SearchBar(isAppBarVisible, mainViewModel)
                }
            }
            else -> {
                AppBarWithArrow(navigationTitle(navController)) {
                    navController.popBackStack()
                }
            }
        }
    }, drawerContent = {
        // Drawer content
        if (genres is DataState.Success<Genres>) {
            DrawerUI(navController, genres.data.genres) {
                genreName.value = it
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    }, floatingActionButton = {
        when (currentRoute(navController)) {
            Screen.Home.route -> {
                FloatingActionButton(
                    onClick = {
                        isAppBarVisible.value = false
                    }, backgroundColor = FloatingActionBackground
                ) {
                    Icon(Icons.Filled.Search, "", tint = Color.White)
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
                if (isAppBarVisible.value.not()) {
                    SearchUI(navController, mainViewModel.searchData) {
                        isAppBarVisible.value = true
                    }
                }
            }
        }
        mainViewModel.searchData.pagingLoadingState {
            searchProgressBar.value = it
        }
    }
}