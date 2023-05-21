package com.example.pillativecareapp.ui.login


import androidx.fragment.app.viewModels
import com.example.pillativecareapp.R
import com.example.pillativecareapp.databinding.FragmentLoginBinding
import com.example.pillativecareapp.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding,LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.fragment_login


}