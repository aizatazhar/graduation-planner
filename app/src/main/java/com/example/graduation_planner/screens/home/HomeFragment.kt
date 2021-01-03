package com.example.graduation_planner.screens.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.graduation_planner.R
import com.example.graduation_planner.models.GraduationRequirements
import com.example.graduation_planner.models.SampleModules

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.home_fragment, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        val tvUlr: TextView = root.findViewById(R.id.tvUlr)
        tvUlr.text = getString(R.string.tvUlrText, viewModel.satisfiesUlr.toString())

        val tvCsFoundations: TextView = root.findViewById(R.id.tvCsFoundations)
        tvCsFoundations.text = getString(R.string.tvCsFoundationsText, viewModel.satisfiesCsFoundations.toString())

        val tvCsBreadthAndDepth: TextView = root.findViewById(R.id.tvCsBreadthAndDepth)
        tvCsBreadthAndDepth.text = getString(R.string.tvCsBreadthAndDepthText, viewModel.satisfiesCsBreadthAndDepth.toString())

        val tvIndustrialExperience: TextView = root.findViewById(R.id.tvIndustrialExperience)
        tvIndustrialExperience.text = getString(R.string.tvIndustrialExperienceText, viewModel.satisfiesIndustrialExperience.toString())

        val tvItProfessionalism: TextView = root.findViewById(R.id.tvItProfessionalism)
        tvItProfessionalism.text = getString(R.string.tvItProfessionalismText, viewModel.satisfiesItProfessionalism.toString())

        val tvMathematicsAndSciences: TextView = root.findViewById(R.id.tvMathematicsAndSciences)
        tvMathematicsAndSciences.text = getString(R.string.tvMathematicsAndSciencesText, viewModel.satisfiesMathematicsAndSciences.toString())

        val tvCredits: TextView = root.findViewById(R.id.tvCredits)
        tvCredits.text = getString(R.string.tvCreditsText, viewModel.satisfiesCredits.toString())

        return root
    }
}