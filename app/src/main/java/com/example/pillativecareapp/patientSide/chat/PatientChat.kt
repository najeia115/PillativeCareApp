package com.example.pillativecareapp.patientSide.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pillativecareapp.R
import com.example.pillativecareapp.adapters.DoctorAdapter
import com.example.pillativecareapp.data.User
import com.example.pillativecareapp.mutual.ChattingScreen
import com.example.pillativecareapp.patientSide.PatientNotifications
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.patient_chat.*

class PatientChat : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    val doctors = ArrayList<User>()
    val myAdapter = DoctorAdapter(doctors, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patient_chat)


        RV_doctors.layoutManager = LinearLayoutManager(this)
        RV_doctors.adapter = myAdapter

        db.collection("doctors")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    doctors.add(
                        User(
                            document.id,
                            document.getString("name").toString(),
                            document.getString("image").toString(),
                        )
                    )
                    Log.e("success", "${document.id} => ${document.data}")
                }
                myAdapter.notifyDataSetChanged()
                if (doctors.isEmpty()) {
                    progressBar.isIndeterminate = true
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.isIndeterminate = false
                    progressBar.visibility = View.GONE
                }
            }
            .addOnFailureListener { exception ->
                Log.e("error", "Error getting documents.", exception)
                Toast.makeText(this, "There is an error getting the data", Toast.LENGTH_SHORT)
            }

//        val storageRef = FirebaseStorage.getInstance().getReference()

        if (doctors.isEmpty()) {
            progressBar.isIndeterminate = true
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.isIndeterminate = false
            progressBar.visibility = View.GONE
        }

        myAdapter.onItemClickListener(object : DoctorAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@PatientChat, ChattingScreen::class.java)
                intent.putExtra("doctorId", doctors[position].id)
                print(doctors[position].id)
                startActivity(intent)
            }
        })
    }
    private fun searchFirestore(query: String) {
        val startValue = query
        val endValue = query + "\uf8ff"
        val collectionRef = db.collection("doctors")
        val queryRef: Query = collectionRef.orderBy("name")
            .whereGreaterThanOrEqualTo("name", startValue)
            .whereLessThanOrEqualTo("name", endValue)
        queryRef.get().addOnSuccessListener { querySnapshot ->
            querySnapshot.documents.map { document ->
                document.toObject(User::class.java)
            }
            myAdapter.filter(query)
        }
    }
    private fun clearSearch() {
        if (myAdapter.isFiltering()) {
            myAdapter.filter("") // Pass an empty query to clear the filter
        }
    }
}