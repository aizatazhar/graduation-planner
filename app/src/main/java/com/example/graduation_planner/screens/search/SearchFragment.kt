package com.example.graduation_planner.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.graduation_planner.R
import com.example.graduation_planner.databinding.SearchFragmentBinding
import com.example.graduation_planner.models.Module
import com.example.graduation_planner.repository.Repository

class SearchFragment : Fragment() {
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SearchViewModel
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

        val repository = Repository(requireActivity().application)
        val viewModelFactory = SearchViewModelFactory(repository)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(SearchViewModel::class.java)

        // Set up our RecyclerView
        viewModel.displayList.value?.let {
            searchRecyclerAdapter = SearchRecyclerAdapter(this::onClickModule, it)
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
    }

    // Passes module code and selectedSemester to ModuleDetailsFragment via FragmentManager,
    // then navigates to the ModuleDetailsFragment
    private fun onClickModule(module: Module) {
        setFragmentResult(
            "searchFragmentKey",
            bundleOf(
                "moduleCode" to module.moduleCode,
                "selectedSemester" to viewModel.selectedSemester
            )
        )
        findNavController().navigate(R.id.action_searchFragment_to_moduleDetailsFragment)
    }

    // Fragments outlive their views so need to clean up references to binding class instance
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}