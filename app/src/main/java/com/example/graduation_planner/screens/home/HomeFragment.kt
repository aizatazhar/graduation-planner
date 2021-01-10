package com.example.graduation_planner.screens.home

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

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var homeRecyclerAdapter: HomeRecyclerAdapter
    private var notSatisfiedColour = Color.parseColor("#ab1100")
    private var satisfiedColour = Color.parseColor("#00ab1c")

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
            tvUlr.text = getString(R.string.tvUlrText, if (viewModel.ulr.value!!) "Yes" else "No")
            setTextViewTextColour(tvUlr, viewModel.ulr.value!!)
        })

        val tvCsFoundations: TextView = root.findViewById(R.id.tvCsFoundations)
        viewModel.csFoundations.observe(viewLifecycleOwner, Observer {
            tvCsFoundations.text = getString(R.string.tvCsFoundationsText, if (viewModel.csFoundations.value!!) "Yes" else "No")
            setTextViewTextColour(tvCsFoundations, viewModel.csFoundations.value!!)
        })

        val tvCsBreadthAndDepth: TextView = root.findViewById(R.id.tvCsBreadthAndDepth)
        viewModel.csBreadthAndDepth.observe(viewLifecycleOwner, Observer {
            tvCsBreadthAndDepth.text = getString(R.string.tvCsBreadthAndDepthText, if (viewModel.csBreadthAndDepth.value!!) "Yes" else "No")
            setTextViewTextColour(tvCsBreadthAndDepth, viewModel.csBreadthAndDepth.value!!)
        })

        val tvIndustrialExperience: TextView = root.findViewById(R.id.tvIndustrialExperience)
        viewModel.industrialExperience.observe(viewLifecycleOwner, Observer {
            tvIndustrialExperience.text = getString(R.string.tvIndustrialExperienceText, if (viewModel.industrialExperience.value!!) "Yes" else "No")
            setTextViewTextColour(tvIndustrialExperience, viewModel.industrialExperience.value!!)
        })

        val tvItProfessionalism: TextView = root.findViewById(R.id.tvItProfessionalism)
        viewModel.itProfessionalism.observe(viewLifecycleOwner, Observer {
            tvItProfessionalism.text = getString(R.string.tvItProfessionalismText, if (viewModel.itProfessionalism.value!!) "Yes" else "No")
            setTextViewTextColour(tvItProfessionalism, viewModel.itProfessionalism.value!!)
        })

        val tvMathematicsAndSciences: TextView = root.findViewById(R.id.tvMathematicsAndSciences)
        viewModel.mathematicsAndSciences.observe(viewLifecycleOwner, Observer {
            tvMathematicsAndSciences.text = getString(R.string.tvMathematicsAndSciencesText, if (viewModel.mathematicsAndSciences.value!!) "Yes" else "No")
            setTextViewTextColour(tvMathematicsAndSciences, viewModel.mathematicsAndSciences.value!!)
        })

        val tvCredits: TextView = root.findViewById(R.id.tvCredits)
        viewModel.credits.observe(viewLifecycleOwner, Observer {
            tvCredits.text = getString(R.string.tvCreditsText, if (viewModel.credits.value!!) "Yes" else "No")
            setTextViewTextColour(tvCredits, viewModel.credits.value!!)
        })

        val fabAddButton: ExtendedFloatingActionButton = root.findViewById(R.id.fabAddModule)
        fabAddButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_home_to_searchFragment)
        }

        return root
    }

    private fun setTextViewTextColour(textView: TextView, isSatisfied: Boolean) {
        if (isSatisfied) {
            textView.setTextColor(satisfiedColour)
        } else {
            textView.setTextColor(notSatisfiedColour)
        }
    }
}