package com.example.pillativecareapp.ui.home.adapters

import com.example.pillativecareapp.R
import com.example.pillativecareapp.data.Topic
import com.example.pillativecareapp.ui.base.BaseAdapter
import com.example.pillativecareapp.ui.core.listener.TopicListener

class HomeAdapter(
    topicsList: List<Topic>,
    listener : TopicListener,
) : BaseAdapter<Topic>(topicsList,listener){
    override val layoutId: Int = R.layout.item_topic_card
}