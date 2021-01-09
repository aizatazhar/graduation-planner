package com.example.graduation_planner.screens.home

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

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var homeRecyclerAdapter: HomeRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.home_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = HomeViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        // Set up our RecyclerView
        recyclerView = root.findViewById(R.id.rvModulesAdded)
        homeRecyclerAdapter = HomeRecyclerAdapter(viewModel::deleteModule, viewModel.modules)
        recyclerView.adapter = homeRecyclerAdapter

        viewModel.liveModules.observe(viewLifecycleOwner, Observer {
            homeRecyclerAdapter.submitList(viewModel.liveModules.value!!)
            viewModel.recalculateGraduationRequirements() // Need to recalculate every time a new module is added or deleted
        })

        val tvUlr: TextView = root.findViewById(R.id.tvUlr)
        viewModel.ulr.observe(viewLifecycleOwner, Observer {
            tvUlr.text = getString(R.string.tvUlrText, viewModel.ulr.value.toString())
        })

        val tvCsFoundations: TextView = root.findViewById(R.id.tvCsFoundations)
        viewModel.csFoundations.observe(viewLifecycleOwner, Observer {
            tvCsFoundations.text = getString(R.string.tvCsFoundationsText, viewModel.csFoundations.value.toString())
        })

        val tvCsBreadthAndDepth: TextView = root.findViewById(R.id.tvCsBreadthAndDepth)
        viewModel.csBreadthAndDepth.observe(viewLifecycleOwner, Observer {
            tvCsBreadthAndDepth.text = getString(R.string.tvCsBreadthAndDepthText, viewModel.csBreadthAndDepth.value.toString())
        })

        val tvIndustrialExperience: TextView = root.findViewById(R.id.tvIndustrialExperience)
        viewModel.industrialExperience.observe(viewLifecycleOwner, Observer {
            tvIndustrialExperience.text = getString(R.string.tvIndustrialExperienceText, viewModel.industrialExperience.value.toString())
        })

        val tvItProfessionalism: TextView = root.findViewById(R.id.tvItProfessionalism)
        viewModel.itProfessionalism.observe(viewLifecycleOwner, Observer {
            tvItProfessionalism.text = getString(R.string.tvItProfessionalismText, viewModel.itProfessionalism.value.toString())
        })

        val tvMathematicsAndSciences: TextView = root.findViewById(R.id.tvMathematicsAndSciences)
        viewModel.mathematicsAndSciences.observe(viewLifecycleOwner, Observer {
            tvMathematicsAndSciences.text = getString(R.string.tvMathematicsAndSciencesText, viewModel.mathematicsAndSciences.value.toString())
        })

        val tvCredits: TextView = root.findViewById(R.id.tvCredits)
        viewModel.credits.observe(viewLifecycleOwner, Observer {
            tvCredits.text = getString(R.string.tvCreditsText, viewModel.credits.value.toString())
        })

        val fabAddButton: ExtendedFloatingActionButton = root.findViewById(R.id.fabAddModule)
        fabAddButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_home_to_searchFragment)
        }

        return root
    }
}