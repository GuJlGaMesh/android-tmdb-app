package com.vsu.movieapp.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import com.vsu.movieapp.R
import com.vsu.movieapp.databinding.ItemPersonBinding
import com.vsu.movieapp.models.entity.Person
import com.vsu.movieapp.view.ui.details.person.PersonDetailActivity

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

  private val items: MutableList<Person> = arrayListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
    val binding = parent.binding<ItemPersonBinding>(R.layout.item_person)
    return PeopleViewHolder(binding).apply {
      binding.root.setOnClickListener {
        val person = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
          ?: return@setOnClickListener
        PersonDetailActivity.startActivity(it.context, items[person])
      }
    }
  }

  override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
    with(holder.binding) {
      person = items[position]
      executePendingBindings()
    }
  }

  override fun getItemCount(): Int = items.size

  fun addPeople(people: List<Person>) {
    val previousItemSize = items.size
    items.addAll(people)
    notifyItemRangeInserted(previousItemSize, people.size)
  }

  class PeopleViewHolder(val binding: ItemPersonBinding) :
    RecyclerView.ViewHolder(binding.root)
}
