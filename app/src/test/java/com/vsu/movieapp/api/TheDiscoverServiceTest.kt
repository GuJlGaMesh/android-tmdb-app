package com.vsu.movieapp.api

import com.skydoves.sandwich.ApiResponse
import com.vsu.movieapp.api.service.TheDiscoverService
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.io.IOException

class TheDiscoverServiceTest : ApiAbstract<TheDiscoverService>() {

  private lateinit var service: TheDiscoverService

  @Before
  fun initService() {
    this.service = createService(TheDiscoverService::class.java)
  }

  @Throws(IOException::class)
  @Test
  fun fetchMovieListTest() = runBlocking {
    enqueueResponse("/tmdb_movie.json")
    when (val response = service.fetchDiscoverMovie(1)) {
      is ApiResponse.Success -> {
        assertThat(response.data?.results?.get(0)?.id, `is`(164558))
        assertThat(response.data?.total_results, `is`(61))
        assertThat(response.data?.total_pages, `is`(4))
      }
    }
  }

  @Throws(IOException::class)
  @Test
  fun fetchTvListTest() = runBlocking {
    enqueueResponse("/tmdb_tv.json")
    when (val response = service.fetchDiscoverTv(1)) {
      is ApiResponse.Success -> {
        assertThat(response.data?.results?.get(0)?.id, `is`(61889))
        assertThat(response.data?.total_results, `is`(61470))
        assertThat(response.data?.total_pages, `is`(3074))
      }
    }
  }
}
