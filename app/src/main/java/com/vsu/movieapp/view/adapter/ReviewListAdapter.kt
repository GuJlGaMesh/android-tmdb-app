package com.vsu.movieapp.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import com.vsu.movieapp.R
import com.vsu.movieapp.databinding.ItemReviewBinding
import com.vsu.movieapp.models.Review

class ReviewListAdapter : RecyclerView.Adapter<ReviewListAdapter.ReviewListViewHolder>() {

  private val items: MutableList<Review> = arrayListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewListViewHolder {
    val binding = parent.binding<ItemReviewBinding>(R.layout.item_review)
    return ReviewListViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ReviewListViewHolder, position: Int) {
    with(holder.binding) {
      review = items[position]
      executePendingBindings()
    }
  }

  override fun getItemCount(): Int = items.size

  fun addReviewList(reviews: List<Review>) {
    items.addAll(reviews)
    notifyItemRangeInserted(items.size + 1, reviews.size)
  }

  class ReviewListViewHolder(val binding: ItemReviewBinding) :
    RecyclerView.ViewHolder(binding.root)
}
