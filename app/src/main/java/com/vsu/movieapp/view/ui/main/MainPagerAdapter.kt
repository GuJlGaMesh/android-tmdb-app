package com.vsu.movieapp.view.ui.main

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPagerAdapter(
  fm: FragmentManager,
  val lifecycle: Lifecycle,
  application: Application,
  val mainActivity: MainActivity
) :
  FragmentStateAdapter(fm, lifecycle) {
    private val vp: FragmentManager = fm
private val app = application
    private var isLoggedIn: Boolean = false;
  override fun createFragment(position: Int): Fragment {
    val authAppRepository = com.vsu.movieapp.repository.AuthAppRepository(app)

    authAppRepository.userLiveData.observe(mainActivity, Observer { firebaseUser ->
      isLoggedIn = firebaseUser != null

    })
    return when (position) {
      0 -> MovieListFragment()
      1 -> TvListFragment()
      2 -> PersonListFragment()
      else -> if (isLoggedIn) com.vsu.movieapp.view.ui.loginregister.view.LoggedInFragment() else com.vsu.movieapp.view.ui.loginregister.view.LoginRegisterFragment()
    }
  }

  override fun getItemCount() = 4
}
