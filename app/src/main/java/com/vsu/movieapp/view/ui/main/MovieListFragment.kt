package com.vsu.movieapp.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.skydoves.bindables.BindingFragment
import com.vsu.movieapp.R
import com.vsu.movieapp.databinding.MainFragmentMovieBinding
import com.vsu.movieapp.view.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment :
  BindingFragment<MainFragmentMovieBinding>(R.layout.main_fragment_movie) {

  private val vm: MainActivityViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    return binding {
      adapter = MovieListAdapter()
      viewModel = vm
    }.root
  }
}
