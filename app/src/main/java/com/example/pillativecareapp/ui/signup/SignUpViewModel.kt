package com.example.pillativecareapp.ui.signup

import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.pillativecareapp.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpViewModel : BaseViewModel() {

    private lateinit var et_email: EditText
    private lateinit var et_password: EditText
    private lateinit var et_name: EditText
    private lateinit var et_cpassword: EditText
    private lateinit var signup: Button
    private lateinit var login: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore




}