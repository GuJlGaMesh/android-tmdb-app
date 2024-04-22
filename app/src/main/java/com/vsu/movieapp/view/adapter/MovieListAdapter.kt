/*
 * Designed and developed by 2019 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
