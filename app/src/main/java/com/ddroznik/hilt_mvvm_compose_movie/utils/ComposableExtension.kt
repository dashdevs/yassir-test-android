package com.ddroznik.hilt_mvvm_compose_movie.utils

import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.DataState

fun <T : Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyGridItemScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}

@Composable
fun <T : Any> LazyPagingItems<T>.pagingLoadingState(
    isLoaded: (pagingState: Boolean) -> Unit,
) {
    this.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                isLoaded(true)
            }
            loadState.append is LoadState.Loading -> {
                isLoaded(true)
            }
            loadState.refresh is LoadState.NotLoading -> {
                isLoaded(false)
            }
            loadState.append is LoadState.NotLoading -> {
                isLoaded(false)
            }
        }
    }
}

fun <T : Any> MutableState<DataState<T>?>.pagingLoadingState(isLoaded: (pagingState: Boolean) -> Unit) {
    when (this.value) {
        is DataState.Success<T> -> {
            isLoaded(false)
        }
        is DataState.Loading -> {
            isLoaded(true)
        }
        is DataState.Error -> {
            isLoaded(false)
        }
        else -> {

        }
    }
}