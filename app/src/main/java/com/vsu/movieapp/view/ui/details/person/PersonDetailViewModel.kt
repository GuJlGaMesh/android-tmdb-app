package com.vsu.movieapp.view.ui.details.person

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import com.vsu.movieapp.models.network.PersonDetail
import com.vsu.movieapp.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel @Inject constructor(
  private val peopleRepository: PeopleRepository
) : BindingViewModel() {

  @get:Bindable
  var isLoading: Boolean by bindingProperty(false)
    private set

  private val personIdSharedFlow: MutableSharedFlow<Int> = MutableSharedFlow(replay = 1)

  private val personFlow: Flow<PersonDetail?> = personIdSharedFlow.flatMapLatest {
    isLoading = true
    peopleRepository.loadPersonDetail(it) {
      isLoading = false
    }
  }

  @get:Bindable
  val person: PersonDetail? by personFlow.asBindingProperty(viewModelScope, null)

  init {
    Timber.d("Injection : PersonDetailViewModel")
  }

  fun postPersonId(id: Int) = personIdSharedFlow.tryEmit(id)
}
