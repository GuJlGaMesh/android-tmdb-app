package com.vsu.movieapp.models.network

import com.vsu.movieapp.models.NetworkResponseModel
import com.vsu.movieapp.models.Review

class ReviewListResponse(
  val id: Int,
  val page: Int,
  val results: List<Review>,
  val total_pages: Int,
  val total_results: Int
) : NetworkResponseModel
