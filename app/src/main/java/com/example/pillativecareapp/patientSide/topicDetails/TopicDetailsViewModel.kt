package com.example.pillativecareapp.patientSide.topicDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pillativecareapp.data.Status
import com.example.pillativecareapp.data.Topic
import com.example.pillativecareapp.patientSide.base.BaseViewModel
import com.example.pillativecareapp.util.FirestoreUtils
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.properties.Delegates

class TopicDetailsViewModel : BaseViewModel() {

    var topicId by Delegates.notNull<String>()
    private lateinit var firestore: FirebaseFirestore
    private val _topic = MutableLiveData<Status<Topic?>>()
    val topic: LiveData<Status<Topic?>> = _topic
    fun loadData() {
        getTopicById(topicId)
    }

    private fun getTopicById(id: String) {
        _topic.postValue(Status.Loading)
        firestore = FirebaseFirestore.getInstance()
        val topicsCollection = firestore.collection("topics")

        topicsCollection.document(id)
            .get()
            .addOnSuccessListener {
                val topic = FirestoreUtils.getTopicFromDocument(it)
                val status = Status.Success(topic)
                Log.e("najeia", status.data.toString())
                onTopicSuccess(status)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    private fun onTopicSuccess(status: Status<Topic?>) {
        status.toData()?.let {
            _topic.postValue(Status.Success(it))
        }
    }

    private fun onFailure(throwable: Throwable) {
        _topic.postValue(Status.Failure(throwable.message.toString()))
    }
}