package com.example.pillativecareapp.patientSide.login


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pillativecareapp.R
import com.example.pillativecareapp.databinding.FragmentLoginBinding
import com.example.pillativecareapp.patientSide.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding,LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()
    private val args: LoginFragmentArgs by navArgs()
    override val layoutIdFragment: Int
        get() = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userType = args.userType
    }

}