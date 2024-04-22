package com.vsu.movieapp.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

@Suppress("unused")
class AndroidJunitTestRunner : AndroidJUnitRunner() {
  @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
  override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
    return super.newApplication(cl, Application::class.java.name, context)
  }
}
