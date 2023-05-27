package com.example.pillativecareapp.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pillativecareapp.R
import com.example.pillativecareapp.databinding.FragmentSignUpBinding
import com.example.pillativecareapp.ui.base.BaseFragment


class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>()  {
    override val viewModel: SignUpViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.fragment_sign_up
    private val args: SignUpFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userType = args.userType
    }
}
