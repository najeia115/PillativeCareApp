package com.example.pillativecareapp.patientSide.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pillativecareapp.data.Status
import com.example.pillativecareapp.data.Topic
import com.example.pillativecareapp.patientSide.base.BaseViewModel
import com.example.pillativecareapp.patientSide.core.listener.TopicListener
import com.example.pillativecareapp.util.FirestoreUtils
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel :  BaseViewModel(), TopicListener {

    private lateinit var firestore: FirebaseFirestore
    private val _topics = MutableLiveData<Status<List<Topic>>>()
    val topics: LiveData<Status<List<Topic>>>
        get() = _topics

    init {
        loadData()
    }

    fun loadData() {
        getTopics()
    }

    private fun getTopics() {

        _topics.postValue(Status.Loading)
        firestore = FirebaseFirestore.getInstance()
        val topicsCollection = firestore.collection("topics")

        topicsCollection.get()
            .addOnSuccessListener { result ->
                val topicList = mutableListOf<Topic>()
                for (document in result) {
                    val topic = FirestoreUtils.getTopicFromDocument(document)
                    if (topic != null) {
                        topicList.add(topic)
                    }
                }
                val status = Status.Success(topicList)
                Log.e("najeia", status.data.toString())
                onTopicsSuccess(status)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    private fun onTopicsSuccess(status: Status<List<Topic>>) {
        status.toData()?.let {
            _topics.postValue(Status.Success(it))
        }
    }

    private fun onFailure(throwable: Throwable) {
        _topics.postValue(Status.Failure(throwable.message.toString()))
    }

    override fun onClickTopic(id: String) {
        navigate(HomeFragmentDirections.actionHomeFragmentToTopicDetailsFragment(id))
    }
}