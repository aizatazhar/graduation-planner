package com.example.graduation_planner.screens.modules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_planner.R
import com.example.graduation_planner.models.Module
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class ModulesFragment : Fragment() {
    private val viewModel: ModulesViewModel by activityViewModels()
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var modulesRecyclerAdapter: ModulesRecyclerAdapter

    private val groupNames = arrayOf("Y1S1", "Y1S2")
    private val map = hashMapOf("Y1S1" to arrayOf(
        Module("CS1101S", "Programming Methodology I"),
        Module("CS1231S", "Discrete Structures")), "Y1S2" to arrayOf(
        Module("CS2040S", "Data Structures & Algorithms"),
        Module("CS2030", "Programming Methodology II")
    ))
    private lateinit var expandableListView: ExpandableListView
    private lateinit var adapter: ModulesExpandableListViewAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?)
            : View? {
        val root = inflater.inflate(R.layout.modules_fragment, container, false)

//        recyclerView = root.findViewById(R.id.rvModulesAdded)
//        modulesRecyclerAdapter = ModulesRecyclerAdapter(viewModel::deleteModule, viewModel.modules)
//        recyclerView.adapter = modulesRecyclerAdapter
//
//        viewModel.liveModules.observe(viewLifecycleOwner, {
//            viewModel.liveModules.value?.let { modules -> modulesRecyclerAdapter.submitList(modules) }
//            viewModel.recalculateGraduationRequirements()
//        })

        expandableListView = root.findViewById(R.id.modules)
        adapter = ModulesExpandableListViewAdapter(requireActivity().application, groupNames, map)
        expandableListView.setAdapter(adapter)

        val fabAddButton: ExtendedFloatingActionButton = root.findViewById(R.id.fabAddModule)
        fabAddButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
        }

        return root
    }
}