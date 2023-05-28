package com.example.pillativecareapp.patientSide.search.adapter

import com.example.pillativecareapp.R
import com.example.pillativecareapp.data.Topic
import com.example.pillativecareapp.patientSide.base.BaseAdapter
import com.example.pillativecareapp.patientSide.core.listener.TopicListener

class SearchAdapter(
    topics: List<Topic>,
    listener: TopicListener
) : BaseAdapter<Topic>(topics, listener) {

    override val layoutId: Int = R.layout.item_topic_card

}