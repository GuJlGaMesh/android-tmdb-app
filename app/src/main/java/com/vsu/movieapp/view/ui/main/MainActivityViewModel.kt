package com.vsu.movieapp.view.ui.main

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import com.vsu.movieapp.models.entity.Movie
import com.vsu.movieapp.models.entity.Person
import com.vsu.movieapp.models.entity.Tv
import com.vsu.movieapp.repository.DiscoverRepository
import com.vsu.movieapp.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
  private val discoverRepository: DiscoverRepository,
  private val peopleRepository: PeopleRepository
) : BindingViewModel() {

  @get:Bindable
  var isMovieListLoading: Boolean by bindingProperty(false)
    private set

  @get:Bindable
  var isTvListLoading: Boolean by bindingProperty(false)
    private set

  @get:Bindable
  var isPeopleListLoading: Boolean by bindingProperty(false)
    private set

  private val moviePageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
  private val movieListFlow = moviePageStateFlow.flatMapLatest {
    isMovieListLoading = true
    discoverRepository.loadMovies(it) {
      isMovieListLoading = false
    }
  }

  @get:Bindable
  val movieList: List<Movie> by movieListFlow.asBindingProperty(viewModelScope, emptyList())

  private val tvPageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
  private val tvListFlow = tvPageStateFlow.flatMapLatest {
    isTvListLoading = true
    discoverRepository.loadTvs(it) {
      isTvListLoading = false
    }
  }

  @get:Bindable
  val tvList: List<Tv> by tvListFlow.asBindingProperty(viewModelScope, emptyList())

  private val peoplePageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
  private val peopleListFlow = peoplePageStateFlow.flatMapLatest {
    isPeopleListLoading = true
    peopleRepository.loadPeople(it) {
      isPeopleListLoading = false
    }
  }

  @get:Bindable
  val peopleList: List<Person> by peopleListFlow.asBindingProperty(viewModelScope, emptyList())

  init {
    Timber.d("injection MainActivityViewModel")
  }

  fun postMoviePage(page: Int) = moviePageStateFlow.tryEmit(page)

  fun postTvPage(page: Int) = tvPageStateFlow.tryEmit(page)

  fun postPeoplePage(page: Int) = peoplePageStateFlow.tryEmit(page)
}
