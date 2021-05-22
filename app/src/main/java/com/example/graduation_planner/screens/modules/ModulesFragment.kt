package com.example.graduation_planner.screens.modules

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_planner.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class ModulesFragment : Fragment() {
    private val viewModel: ModulesViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var modulesRecyclerAdapter: ModulesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View? {
        val root = inflater.inflate(R.layout.modules_fragment, container, false)

        recyclerView = root.findViewById(R.id.rvModulesAdded)
        modulesRecyclerAdapter = ModulesRecyclerAdapter(viewModel::deleteModule, viewModel.modules)
        recyclerView.adapter = modulesRecyclerAdapter

        viewModel.liveModules.observe(viewLifecycleOwner, {
            viewModel.liveModules.value?.let { modules -> modulesRecyclerAdapter.submitList(modules) }
            viewModel.recalculateGraduationRequirements()
        })

        val fabAddButton: ExtendedFloatingActionButton = root.findViewById(R.id.fabAddModule)
        fabAddButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_home_to_searchFragment)
        }

        return root
    }
}