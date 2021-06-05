package com.example.graduation_planner.screens.modules

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.graduation_planner.R
import com.example.graduation_planner.models.Module

class ModulesFragment : Fragment(R.layout.modules_fragment) {
    private val viewModel: ModulesViewModel by activityViewModels()
    private lateinit var expandableListView: ExpandableListView
    private lateinit var adapter: ModulesExpandableListViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val groupNames = viewModel.groupNames
        val moduleMap = HashMap<String, MutableList<Module>>()
        groupNames.forEach { moduleMap[it] = mutableListOf() }
        adapter = ModulesExpandableListViewAdapter(
            requireActivity().application,
            groupNames,
            moduleMap,
            viewModel::deleteModule
        )
        expandableListView = view.findViewById(R.id.modules)
        expandableListView.setAdapter(adapter)

        viewModel.liveModules.observe(viewLifecycleOwner, {
            // Create new map and recalculate graduation requirements
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

        val addButton: Button = view.findViewById(R.id.addModuleButton)
        addButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
        }
    }
}