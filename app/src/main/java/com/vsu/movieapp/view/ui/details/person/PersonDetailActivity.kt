package com.vsu.movieapp.view.ui.details.person

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.vsu.movieapp.R
import com.vsu.movieapp.databinding.ActivityPersonDetailBinding
import com.vsu.movieapp.models.entity.Person
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailActivity :
  BindingActivity<ActivityPersonDetailBinding>(R.layout.activity_person_detail) {

  private val vm: PersonDetailViewModel by viewModels()
  private val intentPerson: Person by bundleNonNull(PERSON_ID)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding {
      activity = this@PersonDetailActivity
      viewModel = vm.apply { postPersonId(intentPerson.id) }
      person = intentPerson
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == android.R.id.home) onBackPressed()
    return false
  }

  companion object {
    const val PERSON_ID = "person"

    fun startActivity(context: Context, person: Person?) {
      context.intentOf<PersonDetailActivity> {
        putExtra(PERSON_ID to person)
        startActivity(context)
      }
    }
  }
}
