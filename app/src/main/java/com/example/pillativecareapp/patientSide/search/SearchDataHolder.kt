package com.example.pillativecareapp.patientSide.search

import com.example.pillativecareapp.data.Topic

data class SearchDataHolder(
    val topics: List<Topic> = emptyList(),
) {
    fun isNotEmpty() = !isEmpty()

    fun isEmpty() = topics.isEmpty()
}


