package com.vsu.movieapp.models.network

import com.vsu.movieapp.models.Keyword
import com.vsu.movieapp.models.NetworkResponseModel

data class KeywordListResponse(
  val id: Int,
  val keywords: List<Keyword>
) : NetworkResponseModel
