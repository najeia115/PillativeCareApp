package com.example.pillativecareapp.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pillativecareapp.data.User
import com.example.pillativecareapp.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpViewModel : BaseViewModel() {

    private val mAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
     val currentUser : User = User("","","","","","")
    fun signUp() {

        currentUser.run {
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() || phoneNumber.isBlank()) {
            Log.e("tagsignuphere", "please fill the inputs ")
        } else if (password != confirmPassword) {
            Log.e("n", "please confirm the password  ")
        } else {
            addNewUser(currentUser)
            Log.e("SuccessYes", currentUser.toString())
        }
    }
    }

    private fun addNewUser(user: User) {
        mAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->

                Log.e("added", user.email)
                if (task.isSuccessful) {
                    val firebaseUser = mAuth.currentUser
                    val userID = firebaseUser?.uid ?: ""

                    Log.e("onSuccess22", "Data added successfully to database: ")

                    val patient = hashMapOf(
                        "id" to userID,
                        "firstName" to user.firstName,
                        "lastName" to user.lastName,
                        "email" to user.email,
                        "password" to user.password,
                        "phoneNumber" to user.phoneNumber,
                        "confirmPassword" to currentUser.confirmPassword
                    )

                    db.collection("patients").add(patient)
                        .addOnSuccessListener { documentReference ->
                            Log.e("onSuccess", "Data added successfully to database: ")
                        }
                        .addOnFailureListener { e ->
                            Log.e("onFailure", "Failed to add database", e)
                        }

                }else{
                    Log.e("onFailure2", "Failed to add database")
                }
            }
    }
}