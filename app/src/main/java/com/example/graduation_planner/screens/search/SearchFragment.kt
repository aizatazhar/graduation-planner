package com.example.graduation_planner.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_planner.R

class SearchFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.search_fragment, container, false)

        // Read moduleList.json and pass the string as an argument to our view model
        val moduleListJsonString = readJsonFromAsset("moduleList.json")
        val viewModelFactory = SearchViewModelFactory(moduleListJsonString)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

        // Set up our RecyclerView
        recyclerView = root.findViewById(R.id.rvModules)
        recyclerAdapter = RecyclerAdapter(viewModel.displayList.value!!)
        recyclerView.adapter = recyclerAdapter

        // Handle search bar logic
        val searchBar = root.findViewById<SearchView>(R.id.svSearchBar)
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchBar.clearFocus()
                if (query != null) {
                    viewModel.filterModules(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.filterModules(newText)
                }
                return true
            }
        })

        // Observe the LiveData of filtered modules and update our RecyclerView accordingly
        viewModel.displayList.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.submitList(it)
        })

        return root
    }

    private fun readJsonFromAsset(fileName: String) : String {
        return requireActivity().assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    }
}