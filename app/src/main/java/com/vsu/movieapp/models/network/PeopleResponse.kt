package com.vsu.movieapp.models.network

import com.vsu.movieapp.models.NetworkResponseModel
import com.vsu.movieapp.models.entity.Person

data class PeopleResponse(
  val page: Int,
  val results: List<Person>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
