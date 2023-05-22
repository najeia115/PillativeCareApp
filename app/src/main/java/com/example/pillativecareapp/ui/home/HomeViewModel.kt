package com.example.pillativecareapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pillativecareapp.data.Status
import com.example.pillativecareapp.data.Topic
import com.example.pillativecareapp.ui.base.BaseViewModel
import com.example.pillativecareapp.ui.core.listener.TopicListener
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel :  BaseViewModel(), TopicListener {

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
//        _topics.postValue(Status.Loading)
        val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        val topicsCollection = db.collection("topics")

        topicsCollection.get()
            .addOnSuccessListener { result ->
                val topicList = mutableListOf<Topic>()
                for (document in result) {
                    val topic = document.toObject(Topic::class.java)
                    topicList.add(topic)
                }
                val status = Status.Success(topicList)
                onTopicsSuccess(status)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    private fun onTopicsSuccess(status: Status<List<Topic>>) {
        status.toData()?.let {
            _topics.postValue(Status.Success(it))
        } ?: _topics.postValue(Status.Failure("No Cashed Data"))
    }

    private fun onFailure(throwable: Throwable) {
        _topics.postValue(Status.Failure(throwable.message.toString()))
    }

    override fun onClickTopic(id: Int) {
  //      navigate(HomeFragmentDirections.actionHomeFragmentToChatFragment(id))
    }
}