package com.vsu.movieapp.view.ui.details.movie

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.vsu.movieapp.R
import com.vsu.movieapp.databinding.ActivityMovieDetailBinding
import com.vsu.movieapp.models.entity.Movie
import com.vsu.movieapp.view.adapter.ReviewListAdapter
import com.vsu.movieapp.view.adapter.VideoListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity :
  BindingActivity<ActivityMovieDetailBinding>(R.layout.activity_movie_detail) {

  private val vm: MovieDetailViewModel by viewModels()
  private val intentMovie: Movie by bundleNonNull(MOVIE_ID)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding {
      activity = this@MovieDetailActivity
      viewModel = vm.apply { getMovieListFromId(intentMovie.id) }
      movie = intentMovie
      videoListAdapter = VideoListAdapter()
      reviewListAdapter = ReviewListAdapter()
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == android.R.id.home) onBackPressed()
    return false
  }

  companion object {
    private const val MOVIE_ID = "movie"
    fun startActivityModel(context: Context?, movie: Movie?) {
      context?.intentOf<MovieDetailActivity> {
        putExtra(MOVIE_ID to movie)
        startActivity(context)
      }
    }
  }
}
