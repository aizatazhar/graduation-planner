package com.example.graduation_planner.screens.module_list

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_planner.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class ModuleListFragment : Fragment() {
    private lateinit var viewModel: ModuleListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var moduleListRecyclerAdapter: ModuleListRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.module_list_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ModuleListViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ModuleListViewModel::class.java)

        // Set up our RecyclerView
        recyclerView = root.findViewById(R.id.rvModulesAdded)
        moduleListRecyclerAdapter = ModuleListRecyclerAdapter(viewModel::deleteModule, viewModel.modules)
        recyclerView.adapter = moduleListRecyclerAdapter

        viewModel.liveModules.observe(viewLifecycleOwner, Observer {
            moduleListRecyclerAdapter.submitList(viewModel.liveModules.value!!)
            viewModel.recalculateGraduationRequirements() // Need to recalculate every time a new module is added or deleted
        })

        val fabAddButton: ExtendedFloatingActionButton = root.findViewById(R.id.fabAddModule)
        fabAddButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
        }

        return root
    }
}