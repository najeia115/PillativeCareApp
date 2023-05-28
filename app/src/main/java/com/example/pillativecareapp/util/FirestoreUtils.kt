package com.example.pillativecareapp.util

import com.example.pillativecareapp.data.Topic
import com.example.pillativecareapp.data.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.gson.Gson

object FirestoreUtils {
    fun getTopicFromDocument(document: DocumentSnapshot): Topic? {
        val gson = Gson()
        val json = gson.toJson(document.data)
        return gson.fromJson(json, Topic::class.java)
    }

    fun getUserFromDocument(document: DocumentSnapshot): User {
        val gson = Gson()
        val json = gson.toJson(document.data)
        return gson.fromJson(json, User::class.java)
    }
}
