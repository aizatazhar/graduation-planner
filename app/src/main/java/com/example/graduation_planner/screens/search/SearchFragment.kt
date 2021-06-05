package com.example.graduation_planner.screens.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_planner.R
import com.google.android.material.chip.Chip

class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchRecyclerAdapter: SearchRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Set up our RecyclerView
        viewModel.displayList.value?.let {
            recyclerView = view.findViewById(R.id.rvModules)
            searchRecyclerAdapter =
                SearchRecyclerAdapter(viewModel::fetchModuleFromApiAndInsertIntoDatabase, it)
            recyclerView.adapter = searchRecyclerAdapter
        }

        // Observe the LiveData of filtered modules and update our RecyclerView accordingly
        viewModel.displayList.observe(viewLifecycleOwner, {
            searchRecyclerAdapter.submitList(it)
        })

        // Handle search bar logic
        val searchBar = view.findViewById<SearchView>(R.id.svSearchBar)
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchBar.clearFocus()
                viewModel.filterModules(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filterModules(newText)
                return true
            }
        })

        val semesterChip: Chip = view.findViewById(R.id.semesterChip)
        semesterChip.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_semesterFragment)
        }

        val filterChip: Chip = view.findViewById(R.id.filterChip)
        filterChip.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_filterFragment)
        }
    }
}