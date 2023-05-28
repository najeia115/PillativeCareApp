package com.example.pillativecareapp.doctorSide.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pillativecareapp.data.Status
import com.example.pillativecareapp.data.Topic
import com.example.pillativecareapp.data.User
import com.example.pillativecareapp.patientSide.base.BaseViewModel
import com.example.pillativecareapp.patientSide.core.listener.TopicListener
import com.example.pillativecareapp.util.FirestoreUtils
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.properties.Delegates

class DoctorsHomeViewModel : BaseViewModel(), TopicListener {

    private lateinit var firestore: FirebaseFirestore

    var doctorEmail by Delegates.notNull<String>()

    private val _topics = MutableLiveData<Status<List<Topic>>>()
    val topics: LiveData<Status<List<Topic>>> = _topics

    private val _user = MutableLiveData<Status<User>>()
    val user: LiveData<Status<User>> = _user

    init {
        loadData()
    }

    fun loadData() {
        getDoctorName(doctorEmail)
        getTopics()
    }

    private fun getDoctorName(email:String) {
        firestore = FirebaseFirestore.getInstance()
        val doctorsCollection = firestore.collection("doctors")
        doctorsCollection.whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { result ->
                val usersList = mutableListOf<User>()
                for (document in result) {
                    val user = FirestoreUtils.getUserFromDocument(document)
                    usersList.add(user)
                }
                val status = Status.Success(usersList)
                Log.e("najeia", status.data.toString())
                status.toData()!!.first().let {
                    _user.postValue(Status.Success(it))
                }
            }.addOnFailureListener {
                onFailure(it)
            }
    }

    private fun getTopics() {

        _topics.postValue(Status.Loading)
        firestore = FirebaseFirestore.getInstance()
        val topicsCollection = firestore.collection("topics")
        Log.e("doctorNameInViewModel", user.value?.toData()?.firstName.toString())

        topicsCollection.whereEqualTo("doctorName", user.value?.toData()?.firstName).get()
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
            }.addOnFailureListener { exception ->
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
    }

}