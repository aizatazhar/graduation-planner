package com.example.graduation_planner.screens.requirements

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.graduation_planner.R
import com.example.graduation_planner.screens.modules.ModulesViewModel

class RequirementsFragment : Fragment(R.layout.requirements_fragment) {
    private val viewModel: ModulesViewModel by activityViewModels()

    private val notSatisfiedColour = Color.parseColor("#ab1100")
    private val satisfiedColour = Color.parseColor("#00ab1c")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tvUlr: TextView = view.findViewById(R.id.tvUlr)
        viewModel.ulr.observe(viewLifecycleOwner, {
            it?.let {
                tvUlr.text = getString(R.string.tvUlrText, if (it) "Yes" else "No")
                setTextViewTextColour(tvUlr, it)
            }
        })

        val tvCsFoundations: TextView = view.findViewById(R.id.tvCsFoundations)
        viewModel.csFoundations.observe(viewLifecycleOwner, {
            it?.let {
                tvCsFoundations.text =
                    getString(R.string.tvCsFoundationsText, if (it) "Yes" else "No")
                setTextViewTextColour(tvCsFoundations, it)
            }
        })

        val tvCsBreadthAndDepth: TextView = view.findViewById(R.id.tvCsBreadthAndDepth)
        viewModel.csBreadthAndDepth.observe(viewLifecycleOwner, {
            it?.let {
                tvCsBreadthAndDepth.text =
                    getString(R.string.tvCsBreadthAndDepthText, if (it) "Yes" else "No")
                setTextViewTextColour(tvCsBreadthAndDepth, it)
            }
        })

        val tvIndustrialExperience: TextView = view.findViewById(R.id.tvIndustrialExperience)
        viewModel.industrialExperience.observe(viewLifecycleOwner, {
            it?.let {
                tvIndustrialExperience.text =
                    getString(R.string.tvIndustrialExperienceText, if (it) "Yes" else "No")
                setTextViewTextColour(tvIndustrialExperience, it)
            }

        })

        val tvItProfessionalism: TextView = view.findViewById(R.id.tvItProfessionalism)
        viewModel.itProfessionalism.observe(viewLifecycleOwner, {
            it?.let {
                tvItProfessionalism.text =
                    getString(R.string.tvItProfessionalismText, if (it) "Yes" else "No")
                setTextViewTextColour(tvItProfessionalism, it)
            }

        })

        val tvMathematicsAndSciences: TextView = view.findViewById(R.id.tvMathematicsAndSciences)
        viewModel.mathematicsAndSciences.observe(viewLifecycleOwner, {
            it?.let {
                tvMathematicsAndSciences.text =
                    getString(R.string.tvMathematicsAndSciencesText, if (it) "Yes" else "No")
                setTextViewTextColour(tvMathematicsAndSciences, it)
            }
        })

        val tvCredits: TextView = view.findViewById(R.id.tvCredits)
        viewModel.credits.observe(viewLifecycleOwner, {
            it?.let {
                tvCredits.text = getString(R.string.tvCreditsText, if (it) "Yes" else "No")
                setTextViewTextColour(tvCredits, it)
            }
        })
    }

    private fun setTextViewTextColour(textView: TextView, isSatisfied: Boolean) {
        if (isSatisfied) {
            textView.setTextColor(satisfiedColour)
        } else {
            textView.setTextColor(notSatisfiedColour)
        }
    }
}