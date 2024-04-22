package com.vsu.movieapp.di

import android.content.Context
import androidx.room.Room
import com.vsu.movieapp.room.AppDatabase
import com.vsu.movieapp.room.MovieDao
import com.vsu.movieapp.room.PeopleDao
import com.vsu.movieapp.room.TvDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

  @Provides
  @Singleton
  fun provideRoomDataBase(@ApplicationContext context: Context): AppDatabase {
    return Room
      .databaseBuilder(context, AppDatabase::class.java, "TheMovies.db")
      .allowMainThreadQueries()
      .build()
  }

  @Provides
  @Singleton
  fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
    return appDatabase.movieDao()
  }

  @Provides
  @Singleton
  fun provideTvDao(appDatabase: AppDatabase): TvDao {
    return appDatabase.tvDao()
  }

  @Provides
  @Singleton
  fun providePeopleDao(appDatabase: AppDatabase): PeopleDao {
    return appDatabase.peopleDao()
  }
}
