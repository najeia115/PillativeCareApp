package com.example.pillativecareapp.doctorSide.Notification

import androidx.fragment.app.viewModels
import com.example.pillativecareapp.R
import com.example.pillativecareapp.databinding.FragmentNotificationBinding
import com.example.pillativecareapp.patientSide.base.BaseFragment

class NotificationFragment : BaseFragment<FragmentNotificationBinding, NotificationViewModel>() {
    override val viewModel: NotificationViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.fragment_notification_doctor

}