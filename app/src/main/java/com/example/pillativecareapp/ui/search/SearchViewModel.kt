package com.example.pillativecareapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pillativecareapp.data.Status
import com.example.pillativecareapp.data.Topic
import com.example.pillativecareapp.ui.base.BaseViewModel
import com.example.pillativecareapp.ui.core.listener.TopicListener
import com.example.pillativecareapp.util.FirestoreUtils
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class SearchViewModel : BaseViewModel(), TopicListener {
    private lateinit var firestore: FirebaseFirestore
    private val searchQuery = BehaviorSubject.createDefault(SearchQuery())

    private val _state = MutableLiveData<Status<SearchDataHolder>>()
    val state: LiveData<Status<SearchDataHolder>> = _state

    var searchText: String?
        get() = searchQuery.value?.query
        set(value) {
            searchQuery.onNext(SearchQuery(query = value?.takeIf { it.isNotBlank() }))
        }

    init {
        applySearch()
    }

    private fun applySearch() {
        searchQuery.debounce(500, TimeUnit.MILLISECONDS).subscribe {
            loadData()
        }.add()
    }

    fun loadData() {
        getAllTopics(searchText)
    }

    private fun getAllTopics(searchText: String?) {
        firestore = FirebaseFirestore.getInstance()
        val topicsCollection = firestore.collection("topics")
        searchText?.let {
            _state.postValue(Status.Loading)
            topicsCollection.whereGreaterThanOrEqualTo("title", it)
                .whereLessThanOrEqualTo("title", it + "\uf8ff")
                .get()
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
    }

    private fun onTopicsSuccess(topics: Status<List<Topic>>) {
        topics.toData()?.let { result ->
            val newState = Status.Success(SearchDataHolder(topics = result))
            _state.postValue(newState)
        }
    }

    private fun onFailure(throwable: Throwable) {
        _state.postValue(Status.Failure(throwable.message.toString()))
    }

    override fun onClickTopic(id: String) {

    }

}
