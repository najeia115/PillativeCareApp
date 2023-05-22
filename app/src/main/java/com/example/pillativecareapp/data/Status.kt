package com.example.pillativecareapp.data

sealed class Status<out T> {
    object Loading : Status<Nothing>()
    data class Failure(val message: String) : Status<Nothing>()
    data class Success<T>(val data: T) : Status<T>()

    fun toData(): T? = if (this is Success) data else null
    fun toEmpty(): Boolean = when (this) {
        is Success -> data is List<*> && data.isEmpty()
        else -> true
    }
}