package com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote

/**
 * Data state for processing api response Loading, Success and Error
 */
sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}