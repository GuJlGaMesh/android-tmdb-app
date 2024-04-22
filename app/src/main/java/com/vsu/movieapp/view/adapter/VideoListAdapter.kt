package com.vsu.movieapp.view.adapter

import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import com.vsu.movieapp.R
import com.vsu.movieapp.api.Api
import com.vsu.movieapp.databinding.ItemVideoBinding
import com.vsu.movieapp.models.Video

class VideoListAdapter : RecyclerView.Adapter<VideoListAdapter.VideoListViewHolder>() {

  private val items: MutableList<Video> = arrayListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListViewHolder {
    val binding = parent.binding<ItemVideoBinding>(R.layout.item_video)
    return VideoListViewHolder(binding).apply {
      binding.root.setOnClickListener {
        val video = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
          ?: return@setOnClickListener
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Api.getYoutubeVideoPath(items[video].key)))
        it.context.startActivity(playVideoIntent)
      }
    }
  }

  override fun onBindViewHolder(holder: VideoListViewHolder, position: Int) {
    with(holder.binding) {
      video = items[position]
      executePendingBindings()
    }
  }

  override fun getItemCount(): Int = items.size

  fun addVideoList(videos: List<Video>) {
    items.addAll(videos)
    notifyItemRangeInserted(items.size + 1, videos.size)
  }

  class VideoListViewHolder(val binding: ItemVideoBinding) :
    RecyclerView.ViewHolder(binding.root)
}
