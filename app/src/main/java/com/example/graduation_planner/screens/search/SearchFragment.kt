package com.example.graduation_planner.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.graduation_planner.R
import com.example.graduation_planner.databinding.SearchFragmentBinding

class SearchFragment : Fragment() {
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var searchRecyclerAdapter: SearchRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up our RecyclerView
        viewModel.displayList.value?.let {
            searchRecyclerAdapter =
                SearchRecyclerAdapter(viewModel::fetchModuleFromApiAndInsertIntoDatabase, it)
            binding.rvModules.adapter = searchRecyclerAdapter
        }

        // Observe the LiveData of filtered modules and update our RecyclerView accordingly
        viewModel.displayList.observe(viewLifecycleOwner, {
            searchRecyclerAdapter.submitList(it)
        })

        // Handle search bar logic
        binding.svSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.svSearchBar.clearFocus()
                viewModel.filterModules(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filterModules(newText)
                return true
            }
        })

        binding.semesterChip.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_semesterFragment)
        }

        binding.filterChip.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_filterFragment)
        }
    }

    // Fragments outlive their views so need to clean up references to binding class instance
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}