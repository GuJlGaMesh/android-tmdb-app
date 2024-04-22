package com.vsu.movieapp.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import com.vsu.movieapp.R
import com.vsu.movieapp.databinding.ItemPosterBinding
import com.vsu.movieapp.models.entity.Movie
import com.vsu.movieapp.view.ui.details.movie.MovieDetailActivity

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

  private val items: MutableList<Movie> = arrayListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
    val binding = parent.binding<ItemPosterBinding>(R.layout.item_poster)
    return MovieListViewHolder(binding).apply {
      binding.root.setOnClickListener {
        val movie = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
          ?: return@setOnClickListener
        MovieDetailActivity.startActivityModel(it.context, items[movie])
      }
    }
  }

  override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
    with(holder.binding) {
      movie = items[position]
      executePendingBindings()
    }
  }

  fun addMovieList(movies: List<Movie>) {
    val previousItemSize = items.size
    items.addAll(movies)
    notifyItemRangeInserted(previousItemSize, movies.size)
  }

  override fun getItemCount(): Int = items.size

  class MovieListViewHolder(val binding: ItemPosterBinding) : RecyclerView.ViewHolder(binding.root)
}
