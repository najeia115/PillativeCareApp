package com.example.pillativecareapp.ui.signup
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.example.pillativecareapp.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpViewModel : BaseViewModel() {

    private val mAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private lateinit var progressBar: ProgressBar

    fun setProgressBar(progressBar: ProgressBar) {
        this.progressBar = progressBar
    }

    fun signUp(firstname: String,lastname: String, email: String, password: String, confirmPassword: String , phonenumber: String) {
        if (firstname.isBlank() || lastname.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() || phonenumber.isBlank()) {
            Log.e("n", "please fill the inputs ")
            return
        }
        if (password != confirmPassword) {
            Log.e("n", "please confirm the password  ")
            return
        }
        progressBar.visibility = View.VISIBLE

        AddNewUser(firstname,lastname,email,password,confirmPassword,phonenumber)
    }

    fun AddNewUser(firstname: String,lastname: String, email:String, password:String , confirmPassword: String , phonenumber: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = mAuth.currentUser
                    val userID = firebaseUser?.uid ?: ""

                    val user = hashMapOf(
                        "id" to userID,
                        "firstname" to firstname,
                        "lastname" to lastname,
                        "email" to email,
                        "password" to password,
                        "phonenumber" to phonenumber,
                        "confirmPassword" to confirmPassword
                    )

                    db.collection("users").document(userID)
                        .set(user)
                        .addOnSuccessListener { documentReference ->
                            Log.e("n", "Data added successfully to database: ")
                        }
                        .addOnFailureListener { e ->
                            Log.e("n", "Failed to add database", e)
                        }

                    progressBar.setVisibility(View.GONE)
                } else {
                    progressBar.setVisibility(View.GONE)
                }
            }
    }
}