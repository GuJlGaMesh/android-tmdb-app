package com.vsu.movieapp.di

import android.app.Application
import com.vsu.movieapp.api.service.MovieService
import com.vsu.movieapp.api.service.PeopleService
import com.vsu.movieapp.api.service.TheDiscoverService
import com.vsu.movieapp.api.service.TvService
import com.vsu.movieapp.repository.DiscoverRepository
import com.vsu.movieapp.repository.MovieRepository
import com.vsu.movieapp.repository.PeopleRepository
import com.vsu.movieapp.repository.TvRepository
import com.vsu.movieapp.room.MovieDao
import com.vsu.movieapp.room.PeopleDao
import com.vsu.movieapp.room.TvDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideDiscoverRepository(
    discoverService: TheDiscoverService,
    movieDao: MovieDao,
    tvDao: TvDao
  ): DiscoverRepository {
    return DiscoverRepository(discoverService, movieDao, tvDao)
  }

  @Provides
  @ViewModelScoped
  fun provideAuthAppRepository(application: Application): com.vsu.movieapp.repository.AuthAppRepository {
    return com.vsu.movieapp.repository.AuthAppRepository(application)
  }

  @Provides
  @ViewModelScoped
  fun provideMovieRepository(
    movieService: MovieService,
    movieDao: MovieDao
  ): MovieRepository {
    return MovieRepository(movieService, movieDao)
  }

  @Provides
  @ViewModelScoped
  fun providePeopleRepository(
    peopleService: PeopleService,
    peopleDao: PeopleDao
  ): PeopleRepository {
    return PeopleRepository(peopleService, peopleDao)
  }

  @Provides
  @ViewModelScoped
  fun provideTvRepository(
    tvService: TvService,
    tvDao: TvDao
  ): TvRepository {
    return TvRepository(tvService, tvDao)
  }
}
