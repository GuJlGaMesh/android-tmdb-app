package com.vsu.movieapp.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import com.vsu.movieapp.api.Api
import com.vsu.movieapp.api.RequestInterceptor
import com.vsu.movieapp.api.service.MovieService
import com.vsu.movieapp.api.service.PeopleService
import com.vsu.movieapp.api.service.TheDiscoverService
import com.vsu.movieapp.api.service.TvService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(RequestInterceptor())
      .addNetworkInterceptor(StethoInterceptor())
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(Api.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideTheDiscoverService(retrofit: Retrofit): TheDiscoverService {
    return retrofit.create(TheDiscoverService::class.java)
  }

  @Provides
  @Singleton
  fun providePeopleService(retrofit: Retrofit): PeopleService {
    return retrofit.create(PeopleService::class.java)
  }

  @Provides
  @Singleton
  fun provideMovieService(retrofit: Retrofit): MovieService {
    return retrofit.create(MovieService::class.java)
  }

  @Provides
  @Singleton
  fun provideTvService(retrofit: Retrofit): TvService {
    return retrofit.create(TvService::class.java)
  }
}
