package com.example.pillativecareapp.patientSide.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.pillativecareapp.R
import com.example.pillativecareapp.databinding.FragmentSearchBinding
import com.example.pillativecareapp.patientSide.base.BaseFragment
import com.example.pillativecareapp.patientSide.search.adapter.SearchAdapter
import com.example.pillativecareapp.util.observeNonNull

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.fragment_search
    private lateinit var adapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchAdapter(emptyList(), viewModel)
        binding.recyclerView.adapter = adapter
        viewModel.loadData()
        viewModel.state.observeNonNull(viewLifecycleOwner) {
            it.toData()?.let {
                adapter.setItems(it.topics)
            }
        }
    }

}