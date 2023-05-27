package com.example.pillativecareapp.doctorSide.home

import androidx.fragment.app.viewModels
import com.example.pillativecareapp.R
import com.example.pillativecareapp.databinding.FragmentDoctorsHomeBinding
import com.example.pillativecareapp.ui.base.BaseFragment

class DoctorsHomeFragment : BaseFragment<FragmentDoctorsHomeBinding,DoctorsHomeViewModel>() {
    override val viewModel: DoctorsHomeViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.fragment_doctors_home

}