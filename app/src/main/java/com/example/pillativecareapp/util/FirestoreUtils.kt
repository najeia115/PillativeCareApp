package com.example.pillativecareapp.util

import com.example.pillativecareapp.data.Topic
import com.google.firebase.firestore.DocumentSnapshot
import com.google.gson.Gson

object FirestoreUtils {
    fun getTopicFromDocument(document: DocumentSnapshot): Topic? {
        val gson = Gson()
        val json = gson.toJson(document.data)
        return gson.fromJson(json, Topic::class.java)
    }
}
