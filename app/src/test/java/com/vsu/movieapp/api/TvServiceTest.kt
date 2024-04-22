//package com.vsu.movieapp.api
//
//import com.skydoves.sandwich.ApiResponse
//import com.vsu.movieapp.api.service.TvService
//import kotlinx.coroutines.runBlocking
//import org.hamcrest.CoreMatchers.`is`
//import org.hamcrest.MatcherAssert.assertThat
//import org.junit.Before
//import org.junit.Test
//import java.io.IOException
//
//class TvServiceTest : ApiAbstract<TvService>() {
//
//  private lateinit var service: TvService
//
//  @Before
//  fun initService() {
//    this.service = createService(TvService::class.java)
//  }
//
//  @Throws(IOException::class)
//  @Test
//  fun fetchTvKeywordsTest() = runBlocking {
//    enqueueResponse("/tmdb_movie_keywords.json")
//    when (val response = service.fetchKeywords(1)) {
//      is ApiResponse.Success -> {
//        assertThat(response.data?.id, `is`(550))
//        assertThat(response.data?.keywords?.get(0)?.id, `is`(825))
//        assertThat(response.data?.keywords?.get(0)?.name, `is`("support group"))
//      }
//    }
//  }
//
//  @Throws(IOException::class)
//  @Test
//  fun fetchTvVideosTest() = runBlocking {
//    enqueueResponse("/tmdb_movie_videos.json")
//    when (val response = service.fetchVideos(1)) {
//      is ApiResponse.Success -> {
//        assertThat(response.data?.id, `is`(550))
//        assertThat(response.data?.results?.get(0)?.id, `is`("533ec654c3a36854480003eb"))
//        assertThat(response.data?.results?.get(0)?.key, `is`("SUXWAEX2jlg"))
//      }
//    }
//  }
//
//  @Throws(IOException::class)
//  @Test
//  fun fetchTvReviewsTest() = runBlocking {
//    enqueueResponse("/tmdb_movie_reviews.json")
//    when (val response = service.fetchReviews(1)) {
//      is ApiResponse.Success -> {
//        assertThat(response.data?.id, `is`(297761))
//        assertThat(response.data?.results?.get(0)?.id, `is`("57a814dc9251415cfb00309a"))
//        assertThat(response.data?.results?.get(0)?.author, `is`("Frank Ochieng"))
//      }
//    }
//  }
//}
