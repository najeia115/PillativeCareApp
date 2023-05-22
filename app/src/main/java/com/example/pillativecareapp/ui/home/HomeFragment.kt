package com.example.pillativecareapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.pillativecareapp.R
import com.example.pillativecareapp.databinding.FragmentHomeBinding
import com.example.pillativecareapp.ui.base.BaseFragment
import com.example.pillativecareapp.ui.home.adapters.HomeAdapter
import com.example.pillativecareapp.util.observeNonNull

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    lateinit var adapter: HomeAdapter
    override val viewModel: HomeViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.fragment_home
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeAdapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerViewHome.adapter = homeAdapter
    }
}