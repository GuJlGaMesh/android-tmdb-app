package com.vsu.movieapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vsu.movieapp.models.entity.Movie
import com.vsu.movieapp.models.entity.Person
import com.vsu.movieapp.models.entity.Tv
import com.vsu.movieapp.room.converters.IntegerListConverter
import com.vsu.movieapp.room.converters.KeywordListConverter
import com.vsu.movieapp.room.converters.ReviewListConverter
import com.vsu.movieapp.room.converters.StringListConverter
import com.vsu.movieapp.room.converters.VideoListConverter

@Database(
  entities = [(Movie::class), (Tv::class), (Person::class)],
  version = 3, exportSchema = false
)
@TypeConverters(
  value = [
    (StringListConverter::class), (IntegerListConverter::class),
    (KeywordListConverter::class), (VideoListConverter::class), (ReviewListConverter::class)
  ]
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
  abstract fun tvDao(): TvDao
  abstract fun peopleDao(): PeopleDao
}
