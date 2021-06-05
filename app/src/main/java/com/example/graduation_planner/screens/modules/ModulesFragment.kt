package com.example.graduation_planner.screens.modules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.graduation_planner.R
import com.example.graduation_planner.databinding.ModulesFragmentBinding
import com.example.graduation_planner.models.Module

class ModulesFragment : Fragment() {
    private var _binding: ModulesFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ModulesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ModulesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupNames = viewModel.groupNames
        val moduleMap = HashMap<String, MutableList<Module>>()
        groupNames.forEach { moduleMap[it] = mutableListOf() }
        val adapter = ModulesExpandableListViewAdapter(
            requireActivity().application,
            groupNames,
            moduleMap,
            viewModel::deleteModule
        )
        binding.modules.setAdapter(adapter)

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

        binding.addModuleButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
        }
    }

    // Fragments outlive their views so need to clean up references to binding class instance
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}