package com.example.pillativecareapp.patientSide.welcome

import androidx.fragment.app.viewModels
import com.example.pillativecareapp.R
import com.example.pillativecareapp.databinding.FragmentWelcomeBinding
import com.example.pillativecareapp.patientSide.base.BaseFragment

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding, WelcomeViewModel>() {
    override val viewModel: WelcomeViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.fragment_welcome


}