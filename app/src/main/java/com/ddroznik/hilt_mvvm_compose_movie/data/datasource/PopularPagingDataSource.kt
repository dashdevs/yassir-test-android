package com.ddroznik.hilt_mvvm_compose_movie.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.local.MovieDB
import com.ddroznik.hilt_mvvm_compose_movie.data.datasource.remote.ApiService
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.MovieItem
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class PopularPagingDataSource @Inject constructor(
    private val apiService: ApiService,
    private val db: MovieDB
) :
    PagingSource<Int, MovieItem>() {

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val nextPage = params.key ?: 1
            val movieList = apiService.popularMovieList(nextPage)
            db.movieDao.insertAll(movieList.results)
            LoadResult.Page(
                data = movieList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (movieList.results.isNotEmpty()) movieList.page + 1 else null
            )
        } catch (exception: IOException) {
            try {
                val movieList = db.movieDao.getAllMovies()
                LoadResult.Page(
                    data = movieList,
                    prevKey = null,
                    nextKey = null
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        } catch (httpException: HttpException) {
            Timber.e("httpException ${httpException.message}")
            return LoadResult.Error(httpException)
        }
    }
}
