package com.vsu.movieapp.models.network

import com.vsu.movieapp.models.NetworkResponseModel
import com.vsu.movieapp.models.Video

data class VideoListResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel
