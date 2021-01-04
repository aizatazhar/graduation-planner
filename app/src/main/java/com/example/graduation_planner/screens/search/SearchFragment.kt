package com.example.graduation_planner.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.graduation_planner.R

class SearchFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.search_fragment, container, false)

//        val searchBar: SearchView = root.findViewById(R.id.svSearchBar)
//        searchBar.setOnClickListener { searchBar.isIconified = false }

        return root
    }
}