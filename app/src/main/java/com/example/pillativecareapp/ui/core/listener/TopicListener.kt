package com.example.pillativecareapp.ui.core.listener

import com.example.pillativecareapp.ui.base.BaseAdapter

interface TopicListener : BaseAdapter.BaseInteractionListener {
    fun onClickTopic(id: Int)
}