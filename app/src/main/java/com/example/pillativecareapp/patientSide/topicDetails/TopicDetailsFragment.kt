package com.example.pillativecareapp.patientSide.topicDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pillativecareapp.R
import com.example.pillativecareapp.databinding.FragmentTopicDetailsBinding
import com.example.pillativecareapp.patientSide.base.BaseFragment

class TopicDetailsFragment : BaseFragment<FragmentTopicDetailsBinding, TopicDetailsViewModel>() {
    override val viewModel: TopicDetailsViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.fragment_topic_details
    private val args: TopicDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.topicId = args.id
        viewModel.loadData()
    }
}