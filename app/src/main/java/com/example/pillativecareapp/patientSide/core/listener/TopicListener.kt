package com.example.pillativecareapp.patientSide.core.listener

import com.example.pillativecareapp.patientSide.base.BaseAdapter

interface TopicListener : BaseAdapter.BaseInteractionListener {
    fun onClickTopic(id: String)
}