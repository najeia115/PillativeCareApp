package com.example.pillativecareapp.ui.search

import com.example.pillativecareapp.data.Topic

data class SearchDataHolder(
    val topics: List<Topic> = emptyList(),
) {
    fun isNotEmpty() = !isEmpty()

    fun isEmpty() = topics.isEmpty()
}


