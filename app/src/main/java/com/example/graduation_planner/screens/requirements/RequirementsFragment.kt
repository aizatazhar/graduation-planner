package com.example.graduation_planner.screens.requirements

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.graduation_planner.R
import com.example.graduation_planner.screens.modules.ModulesViewModel

class RequirementsFragment : Fragment() {
    private val viewModel: ModulesViewModel by activityViewModels()

    private var notSatisfiedColour = Color.parseColor("#ab1100")
    private var satisfiedColour = Color.parseColor("#00ab1c")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.requirements_fragment, container, false)

        val tvUlr: TextView = root.findViewById(R.id.tvUlr)
        viewModel.ulr.observe(viewLifecycleOwner, {
            tvUlr.text = getString(R.string.tvUlrText, if (viewModel.ulr.value!!) "Yes" else "No")
            setTextViewTextColour(tvUlr, viewModel.ulr.value!!)
        })

        val tvCsFoundations: TextView = root.findViewById(R.id.tvCsFoundations)
        viewModel.csFoundations.observe(viewLifecycleOwner, {
            tvCsFoundations.text = getString(R.string.tvCsFoundationsText, if (viewModel.csFoundations.value!!) "Yes" else "No")
            setTextViewTextColour(tvCsFoundations, viewModel.csFoundations.value!!)
        })

        val tvCsBreadthAndDepth: TextView = root.findViewById(R.id.tvCsBreadthAndDepth)
        viewModel.csBreadthAndDepth.observe(viewLifecycleOwner, {
            tvCsBreadthAndDepth.text = getString(R.string.tvCsBreadthAndDepthText, if (viewModel.csBreadthAndDepth.value!!) "Yes" else "No")
            setTextViewTextColour(tvCsBreadthAndDepth, viewModel.csBreadthAndDepth.value!!)
        })

        val tvIndustrialExperience: TextView = root.findViewById(R.id.tvIndustrialExperience)
        viewModel.industrialExperience.observe(viewLifecycleOwner, {
            tvIndustrialExperience.text = getString(R.string.tvIndustrialExperienceText, if (viewModel.industrialExperience.value!!) "Yes" else "No")
            setTextViewTextColour(tvIndustrialExperience, viewModel.industrialExperience.value!!)
        })

        val tvItProfessionalism: TextView = root.findViewById(R.id.tvItProfessionalism)
        viewModel.itProfessionalism.observe(viewLifecycleOwner, {
            tvItProfessionalism.text = getString(R.string.tvItProfessionalismText, if (viewModel.itProfessionalism.value!!) "Yes" else "No")
            setTextViewTextColour(tvItProfessionalism, viewModel.itProfessionalism.value!!)
        })

        val tvMathematicsAndSciences: TextView = root.findViewById(R.id.tvMathematicsAndSciences)
        viewModel.mathematicsAndSciences.observe(viewLifecycleOwner, {
            tvMathematicsAndSciences.text = getString(R.string.tvMathematicsAndSciencesText, if (viewModel.mathematicsAndSciences.value!!) "Yes" else "No")
            setTextViewTextColour(tvMathematicsAndSciences, viewModel.mathematicsAndSciences.value!!)
        })

        val tvCredits: TextView = root.findViewById(R.id.tvCredits)
        viewModel.credits.observe(viewLifecycleOwner, {
            tvCredits.text = getString(R.string.tvCreditsText, if (viewModel.credits.value!!) "Yes" else "No")
            setTextViewTextColour(tvCredits, viewModel.credits.value!!)
        })

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