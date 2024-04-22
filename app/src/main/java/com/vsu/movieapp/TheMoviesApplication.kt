package com.vsu.movieapp

import android.app.Application
import com.facebook.stetho.Stetho
import com.skydoves.sandwich.SandwichInitializer
import com.vsu.movieapp.operator.GlobalResponseOperator
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TheMoviesApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    // initialize global sandwich operator
    SandwichInitializer.sandwichOperator = GlobalResponseOperator<Any>(this)

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }

    Stetho.initializeWithDefaults(this)
  }
}
