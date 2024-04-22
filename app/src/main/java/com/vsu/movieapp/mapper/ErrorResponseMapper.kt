package com.vsu.movieapp.mapper

import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.vsu.movieapp.models.network.TheMovieErrorResponse

/**
 * A mapper for mapping [ApiResponse.Failure.Error] response as custom [TheMovieErrorResponse] instance.
 *
 * @see [ApiErrorModelMapper](https://github.com/skydoves/sandwich#apierrormodelmapper)
 */
object ErrorResponseMapper : ApiErrorModelMapper<TheMovieErrorResponse> {

  /**
   * maps the [ApiResponse.Failure.Error] to the [TheMovieErrorResponse] using the mapper.
   *
   * @param apiErrorResponse The [ApiResponse.Failure.Error] error response from the network request.
   * @return A customized [TheMovieErrorResponse] error response.
   */
  override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): TheMovieErrorResponse {
    return TheMovieErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
  }
}
