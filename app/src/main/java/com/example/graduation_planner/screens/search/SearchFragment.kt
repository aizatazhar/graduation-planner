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
import com.example.graduation_planner.models.Module
import com.google.gson.GsonBuilder

class SearchFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.search_fragment, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        recyclerView = root.findViewById(R.id.rvModules)
        recyclerAdapter = viewModel.displayList.value?.let { RecyclerAdapter(it) }!!
        recyclerView.adapter = recyclerAdapter

        val searchBar = root.findViewById<SearchView>(R.id.svSearchBar)
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchBar.clearFocus()
                if (query != null) {
                    viewModel.findModules(query)
                }
                return true
            }

            // We only want to query when the submit button is pressed
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        viewModel.displayList.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.submitList(it)
        })

        return root
    }

    private fun readJsonFromAsset(fileName: String) : List<Module> {
        val jsonString: String? = activity?.assets?.open(fileName)?.bufferedReader()?.use {
            it.readText()
        }
        val gson = GsonBuilder().create()

        return gson.fromJson(jsonString, Array<Module>::class.java).toList()
    }
}