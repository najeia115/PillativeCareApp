package com.example.pillativecareapp.doctorSide.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pillativecareapp.R
import com.example.pillativecareapp.databinding.FragmentDoctorsHomeBinding
import com.example.pillativecareapp.patientSide.base.BaseFragment
import com.example.pillativecareapp.doctorSide.home.adapter.HomeAdapter
import com.example.pillativecareapp.util.observeNonNull

class DoctorsHomeFragment : BaseFragment<FragmentDoctorsHomeBinding,DoctorsHomeViewModel>() {
    lateinit var homeAdapter: HomeAdapter
    private val args: DoctorsHomeFragmentArgs by navArgs()
    override val viewModel: DoctorsHomeViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.doctors_home_fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.doctorEmail = args.Email
        homeAdapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerViewDoctorsHome.adapter = homeAdapter

        viewModel.topics.observeNonNull(viewLifecycleOwner) { status ->
            status.toData()?.let {
                homeAdapter.setItems(it)
                Log.e("this",it.toString())
            }
        }
    }

}