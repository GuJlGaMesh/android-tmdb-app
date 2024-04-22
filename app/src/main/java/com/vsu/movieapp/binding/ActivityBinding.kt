package com.vsu.movieapp.binding

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.vsu.movieapp.R
import com.vsu.movieapp.extension.getStatusBarSize

object ActivityBinding {

  private fun AppCompatActivity.simpleToolbarWithHome(toolbar: MaterialToolbar, title_: String = "") {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
      setDisplayHomeAsUpEnabled(true)
      setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
      title = title_
    }
    if (toolbar.layoutParams is CollapsingToolbarLayout.LayoutParams) {
      toolbar.layoutParams = (toolbar.layoutParams as CollapsingToolbarLayout.LayoutParams).apply {
        topMargin = getStatusBarSize()
      }
    }
  }

  @JvmStatic
  @BindingAdapter("simpleToolbarWithHome", "simpleToolbarTitle")
  fun bindToolbarWithTitle(toolbar: MaterialToolbar, activity: AppCompatActivity, title: String) {
    activity.simpleToolbarWithHome(toolbar, title)
  }
}
