package com.example.pillativecareapp.ui.signup

import androidx.fragment.app.viewModels
import com.example.pillativecareapp.R
import com.example.pillativecareapp.databinding.FragmentSignUpBinding
import com.example.pillativecareapp.ui.base.BaseFragment


class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>()  {
    override val viewModel: SignUpViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.fragment_sign_up


}