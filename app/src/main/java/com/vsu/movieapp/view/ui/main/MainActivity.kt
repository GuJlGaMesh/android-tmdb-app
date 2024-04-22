package com.vsu.movieapp.view.ui.main

import android.os.Bundle
import com.skydoves.bindables.BindingActivity
import com.vsu.movieapp.R
import com.vsu.movieapp.databinding.ActivityMainBinding
import com.vsu.movieapp.extension.applyOnPageSelected
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

  private var isLoggedIn: Boolean = false;
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initializeUI()
  }

  private fun initializeUI() {
    with(binding.mainViewpager) {
      adapter = MainPagerAdapter(supportFragmentManager, lifecycle, application, this@MainActivity)
      offscreenPageLimit = 4
      applyOnPageSelected { binding.mainBottomNavigation.menu.getItem(it).isChecked = true }
      binding.mainBottomNavigation.setOnNavigationItemSelectedListener {
        when (it.itemId) {
          R.id.action_one -> currentItem = 0
          R.id.action_two -> currentItem = 1
          R.id.action_three -> currentItem = 2
          R.id.action_four -> currentItem = 3
        }
        true
      }
    }
  }
}
