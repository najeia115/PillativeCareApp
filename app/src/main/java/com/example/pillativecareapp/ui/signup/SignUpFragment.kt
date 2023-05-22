package com.example.pillativecareapp.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pillativecareapp.R
import com.example.pillativecareapp.databinding.FragmentSignUpBinding
import com.example.pillativecareapp.ui.base.BaseFragment


class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>()  {
    override val viewModel: SignUpViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.fragment_sign_up
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}
