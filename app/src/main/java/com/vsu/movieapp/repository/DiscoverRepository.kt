package com.vsu.movieapp.repository

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.suspendOnSuccess
import com.vsu.movieapp.api.service.TheDiscoverService
import com.vsu.movieapp.room.MovieDao
import com.vsu.movieapp.room.TvDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import timber.log.Timber

class DiscoverRepository constructor(
  private val discoverService: TheDiscoverService,
  private val movieDao: MovieDao,
  private val tvDao: TvDao
) : Repository {

  init {
    Timber.d("Injection DiscoverRepository")
  }

  @WorkerThread
  fun loadMovies(page: Int, success: () -> Unit) = flow {
    var movies = movieDao.getMovieList(page)
    if (movies.isEmpty()) {
      val response = discoverService.fetchDiscoverMovie(page)
      response.suspendOnSuccess {
        movies = data.results
        movies.forEach { it.page = page }
        movieDao.insertMovieList(movies)
        emit(movies)
      }
    } else {
      emit(movies)
    }
  }.onCompletion { success() }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadTvs(page: Int, success: () -> Unit) = flow {
    var tvs = tvDao.getTvList(page)
    if (tvs.isEmpty()) {
      val response = discoverService.fetchDiscoverTv(page)
      response.suspendOnSuccess {
        tvs = data.results
        tvs.forEach { it.page = page }
        tvDao.insertTv(tvs)
        emit(tvs)
      }
    } else {
      emit(tvs)
    }
  }.onCompletion { success() }.flowOn(Dispatchers.IO)
}
