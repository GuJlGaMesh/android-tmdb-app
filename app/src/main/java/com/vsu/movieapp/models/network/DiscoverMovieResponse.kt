package com.vsu.movieapp.models.network

import com.vsu.movieapp.models.NetworkResponseModel
import com.vsu.movieapp.models.entity.Movie

data class DiscoverMovieResponse(
  val page: Int,
  val results: List<Movie>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
