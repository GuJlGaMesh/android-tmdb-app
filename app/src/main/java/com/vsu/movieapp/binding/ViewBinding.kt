package com.vsu.movieapp.binding

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.vsu.movieapp.api.Api
import com.vsu.movieapp.extension.requestGlideListener
import com.vsu.movieapp.extension.visible
import com.vsu.movieapp.models.entity.Movie
import com.vsu.movieapp.models.entity.Person
import com.vsu.movieapp.models.entity.Tv
import com.vsu.movieapp.models.network.PersonDetail
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullOrEmpty

object ViewBinding {

  @JvmStatic
  @BindingAdapter("loadCircleImage")
  fun bindLoadCircleImage(view: AppCompatImageView, url: String) {
    Glide.with(view.context)
      .load(url)
      .apply(RequestOptions().circleCrop())
      .into(view)
  }

  @JvmStatic
  @BindingAdapter("loadPaletteImage", "loadPaletteTarget")
  fun bindLoadImage(view: AppCompatImageView, url: String, targetView: View) {
    Glide.with(view)
      .load(url)
      .listener(
        GlidePalette.with(url)
          .use(BitmapPalette.Profile.VIBRANT)
          .intoBackground(targetView)
          .crossfade(true)
      )
      .into(view)
  }

  @JvmStatic
  @BindingAdapter("visibilityByResource")
  fun bindVisibilityByResource(view: View, anyList: List<Any>?) {
    anyList.whatIfNotNullOrEmpty {
      view.visible()
    }
  }

  @JvmStatic
  @BindingAdapter("biography")
  fun bindBiography(view: TextView, personDetail: PersonDetail?) {
    view.text = personDetail?.biography
  }

  @JvmStatic
  @SuppressLint("SetTextI18n")
  @BindingAdapter("bindReleaseDate")
  fun bindReleaseDate(view: TextView, movie: Movie) {
    view.text = "Release Date : ${movie.release_date}"
  }

  @JvmStatic
  @SuppressLint("SetTextI18n")
  @BindingAdapter("bindAirDate")
  fun bindAirDate(view: TextView, tv: Tv) {
    view.text = "First Air Date : ${tv.first_air_date}"
  }

  @JvmStatic
  @BindingAdapter("bindBackDrop")
  fun bindBackDrop(view: ImageView, movie: Movie) {
    movie.backdrop_path.whatIfNotNull(
      whatIf = {
        Glide.with(view.context).load(Api.getBackdropPath(it))
          .listener(view.requestGlideListener())
          .into(view)
      },
      whatIfNot = {
        Glide.with(view.context).load(Api.getBackdropPath(movie.poster_path))
          .listener(view.requestGlideListener())
          .into(view)
      }
    )
  }

  @JvmStatic
  @BindingAdapter("bindBackDrop")
  fun bindBackDrop(view: ImageView, tv: Tv) {
    tv.backdrop_path.whatIfNotNull(
      whatIf = {
        Glide.with(view.context).load(Api.getBackdropPath(it))
          .listener(view.requestGlideListener())
          .into(view)
      },
      whatIfNot = {
        Glide.with(view.context).load(Api.getBackdropPath(tv.poster_path))
          .listener(view.requestGlideListener())
          .into(view)
      }
    )
  }

  @JvmStatic
  @BindingAdapter("bindBackDrop")
  fun bindBackDrop(view: ImageView, person: Person) {
    person.profile_path.whatIfNotNull {
      Glide.with(view.context).load(Api.getBackdropPath(it))
        .apply(RequestOptions().circleCrop())
        .into(view)
    }
  }
}
