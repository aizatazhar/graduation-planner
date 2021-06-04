package com.example.graduation_planner.screens.modules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_planner.R
import com.example.graduation_planner.models.Module
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class ModulesFragment : Fragment() {
    private val viewModel: ModulesViewModel by activityViewModels()
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var modulesRecyclerAdapter: ModulesRecyclerAdapter

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


        val groupNames = viewModel.groupNames
        val moduleMap = HashMap<String, MutableList<Module>>()
        groupNames.forEach{ moduleMap[it] = mutableListOf() }
        adapter = ModulesExpandableListViewAdapter(requireActivity().application, groupNames, moduleMap, viewModel::deleteModule)
        expandableListView = root.findViewById(R.id.modules)
        expandableListView.setAdapter(adapter)

        viewModel.liveModules.observe(viewLifecycleOwner, {
            val modules = viewModel.liveModules.value
            val map = HashMap<String, MutableList<Module>>()
            modules?.forEach {
                val list = map[it.selectedSemester] ?: mutableListOf()
                list.add(it)
                map[it.selectedSemester] = list
            }
            adapter.setModuleMap(map)
            viewModel.recalculateGraduationRequirements()
        })


        val fabAddButton: ExtendedFloatingActionButton = root.findViewById(R.id.fabAddModule)
        fabAddButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
        }

        return root
    }
}