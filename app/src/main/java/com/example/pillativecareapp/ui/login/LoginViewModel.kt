package com.example.pillativecareapp.ui.login
import android.util.Log
import com.example.pillativecareapp.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : BaseViewModel() {
        private val auth: FirebaseAuth = FirebaseAuth.getInstance()
        fun login(email: String, password: String) {
            // Check if email and password are empty
            if (email.isEmpty() || password.isEmpty()) {
                Log.e("n", "please fill the inputs ")
                return
            }
            // Sign in with email and password
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.e("n", "Welcome ")
                    } else {
                        Log.e("n", "Login Failed ")
                    }
                }
        }
    }
