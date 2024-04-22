package com.vsu.movieapp.room.converters

import com.vsu.movieapp.models.Keyword

object KeywordListMapper {
  fun mapToStringList(keywords: List<Keyword>): List<String> {
    var list: MutableList<String> = ArrayList()
    for (keyword in keywords) {
      list.add(keyword.name)
    }
    if (list.size > 7) {
      list = list.subList(0, 6)
    }
    return list
  }
}
