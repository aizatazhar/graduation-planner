package com.example.graduation_planner.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_planner.R

class SearchFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchRecyclerAdapter: SearchRecyclerAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?)
            : View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.search_fragment, container, false)

        // Set up our view model
        val application = requireNotNull(this.activity).application
        val viewModelFactory = SearchViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

        // Set up our RecyclerView
        viewModel.displayList.value?.let {
            recyclerView = root.findViewById(R.id.rvModules)
            searchRecyclerAdapter = SearchRecyclerAdapter(viewModel::fetchModuleFromApiAndInsertIntoDatabase, it)
            recyclerView.adapter = searchRecyclerAdapter
        }

        // Observe the LiveData of filtered modules and update our RecyclerView accordingly
        viewModel.displayList.observe(viewLifecycleOwner, {
            searchRecyclerAdapter.submitList(it)
        })

        // Handle search bar logic
        val searchBar = root.findViewById<SearchView>(R.id.svSearchBar)
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

        return root
    }
}