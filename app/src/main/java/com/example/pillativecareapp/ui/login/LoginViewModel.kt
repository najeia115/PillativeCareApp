package com.example.pillativecareapp.ui.login

import android.util.Log
import com.example.pillativecareapp.data.User
import com.example.pillativecareapp.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlin.properties.Delegates

class LoginViewModel : BaseViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val currentUser: User = User("", "", "", "", "", "", "")
    var userType by Delegates.notNull<String>()
    fun login() {
        Log.e("loginusertype", userType.toString())
        if (currentUser.email.isEmpty() || currentUser.password.isEmpty()) {
            Log.e("n", "please fill the inputs ")
        }
        auth.signInWithEmailAndPassword(currentUser.email, currentUser.password)
            .addOnCompleteListener { task ->
                Log.e("login1", "Welcome")
                if (task.isSuccessful) {
                    Log.e("successLogin", "Welcome ${currentUser.firstName}")
                    if (userType == "patient") {
                        navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    } else {
                        navigate(LoginFragmentDirections.actionLoginFragmentToDoctorsHomeFragment())
                    }
                }else{
                    Log.e("failurelogin", " Failed")
                }
            }
    }
        fun navigateToSignUp() {
            navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment(userType))
        }
    }
