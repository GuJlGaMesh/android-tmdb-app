package com.vsu.movieapp.api

import com.skydoves.sandwich.ApiResponse
import com.vsu.movieapp.api.service.PeopleService
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.io.IOException

class PeopleServiceTest : ApiAbstract<PeopleService>() {

  private lateinit var service: PeopleService

  @Before
  fun initService() {
    this.service = createService(PeopleService::class.java)
  }

  @Throws(IOException::class)
  @Test
  fun fetchPersonListTest() = runBlocking {
    enqueueResponse("/tmdb_people.json")
    when (val response = service.fetchPopularPeople(1)) {
      is ApiResponse.Success -> {
        assertThat(response.data?.results?.get(0)?.id, `is`(28782))
        assertThat(response.data?.total_pages, `is`(984))
        assertThat(response.data?.total_results, `is`(19671))
      }
    }
  }

  @Throws(IOException::class)
  @Test
  fun fetchPersonDetail() = runBlocking {
    enqueueResponse("tmdb_person.json")
    when (val response = service.fetchPersonDetail(123)) {
      is ApiResponse.Success -> {
        assertThat(response.data?.birthday, `is`("1963-12-18"))
        assertThat(response.data?.known_for_department, `is`("Acting"))
        assertThat(response.data?.place_of_birth, `is`("Shawnee, Oklahoma, USA"))
      }
    }
  }
}
