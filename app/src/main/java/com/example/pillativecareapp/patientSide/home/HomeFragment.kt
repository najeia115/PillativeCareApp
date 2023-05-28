package com.example.pillativecareapp.patientSide.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.pillativecareapp.R
import com.example.pillativecareapp.databinding.FragmentHomeBinding
import com.example.pillativecareapp.patientSide.base.BaseFragment
import com.example.pillativecareapp.patientSide.home.adapters.HomeAdapter
import com.example.pillativecareapp.util.observeNonNull

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    lateinit var homeAdapter: HomeAdapter
    override val viewModel: HomeViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.fragment_home
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerViewHome.adapter = homeAdapter

        viewModel.topics.observeNonNull(viewLifecycleOwner) { status ->
            status.toData()?.let {
                homeAdapter.setItems(it)
                Log.e("this",it.toString())
            }
        }
    }
}