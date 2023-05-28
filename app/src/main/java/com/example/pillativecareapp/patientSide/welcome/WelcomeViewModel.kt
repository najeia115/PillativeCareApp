package com.example.pillativecareapp.patientSide.welcome

import com.example.pillativecareapp.patientSide.base.BaseViewModel

class WelcomeViewModel : BaseViewModel(), WelcomeListener {

    override fun onClickPatientButton() {
        navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment(userType = "patient"))
    }

    override fun onClickDoctorButton() {
        navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment(userType = "doctor"))
    }
}