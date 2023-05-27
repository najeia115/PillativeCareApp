package com.example.pillativecareapp.ui.welcome

import com.example.pillativecareapp.ui.base.BaseViewModel

class WelcomeViewModel : BaseViewModel(), WelcomeListener {

    override fun onClickPatientButton() {
        navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment(userType = "patient"))
    }

    override fun onClickDoctorButton() {
        navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment(userType = "doctor"))
    }
}