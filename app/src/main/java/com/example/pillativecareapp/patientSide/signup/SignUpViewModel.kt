package com.example.pillativecareapp.patientSide.signup

import android.util.Log
import com.example.pillativecareapp.data.User
import com.example.pillativecareapp.patientSide.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.properties.Delegates

class SignUpViewModel : BaseViewModel() {

    private val mAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    val currentUser: User = User("", "", "", "", "", "", "")
    var userType by Delegates.notNull<String>()
    fun signUp() {

        currentUser.run {
            if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() || phoneNumber.isBlank()) {
                Log.e("tagsignuphere", "please fill the inputs ")
            } else if (password != confirmPassword) {
                Log.e("n", "please confirm the password  ")
            } else {
                addNewUser(currentUser)
                Log.e("SuccessYes", currentUser.toString())
                if (userType == "Patient") {
                    navigate(SignUpFragmentDirections.actionSignUpFragmentToHomeFragment())
                } else {
                    navigate(SignUpFragmentDirections.actionSignUpFragmentToDoctorsHomeFragment(currentUser.email))
                }
            }
        }
    }

    private fun addNewUser(user: User) {
        mAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    val firebaseUser = mAuth.currentUser
                    val userID = firebaseUser?.uid ?: ""

                    Log.e("authenticationSuccess", "Authenticated Successfully! ")

                    if (userType == "Patient") {
                        val patient = hashMapOf(
                            "id" to userID,
                            "firstName" to user.firstName,
                            "lastName" to user.lastName,
                            "email" to user.email,
                            "password" to user.password,
                            "phoneNumber" to user.phoneNumber,
                            "confirmPassword" to currentUser.confirmPassword,
                            "userType" to userType
                        )

                        db.collection("patients").add(patient)
                            .addOnSuccessListener { documentReference ->
                                Log.e("onSuccessPatient", "Patient Data added successfully ")
                            }
                            .addOnFailureListener { e ->
                                Log.e("onFailurePatient", "Failed to add the patient", e)
                            }
                    } else {
                        val doctor = hashMapOf(
                            "id" to userID,
                            "firstName" to user.firstName,
                            "lastName" to user.lastName,
                            "email" to user.email,
                            "password" to user.password,
                            "phoneNumber" to user.phoneNumber,
                            "confirmPassword" to currentUser.confirmPassword,
                            "userType" to userType
                        )

                        db.collection("doctors").add(doctor)
                            .addOnSuccessListener { documentReference ->
                                Log.e("onSuccessDoctor", "Doctor Data added successfully ")
                            }
                            .addOnFailureListener { e ->
                                Log.e("onFailureDoctor", "Failed to add the doctor", e)
                            }
                    }

                } else {
                    Log.e("authenticationFailure", "Failed to add database")
                }
            }
    }

    fun navigateToLogin() {
        navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment(userType))
    }
}