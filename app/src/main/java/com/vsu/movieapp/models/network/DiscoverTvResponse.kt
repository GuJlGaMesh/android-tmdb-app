package com.vsu.movieapp.models.network

import com.vsu.movieapp.models.NetworkResponseModel
import com.vsu.movieapp.models.entity.Tv

data class DiscoverTvResponse(
  val page: Int,
  val results: List<Tv>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
