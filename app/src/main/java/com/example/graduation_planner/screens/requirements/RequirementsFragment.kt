package com.example.graduation_planner.screens.requirements

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.graduation_planner.R
import com.example.graduation_planner.databinding.RequirementsFragmentBinding
import com.example.graduation_planner.screens.modules.ModulesViewModel

class RequirementsFragment : Fragment(R.layout.requirements_fragment) {
    private var _binding: RequirementsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ModulesViewModel by activityViewModels()

    private val notSatisfiedColour = Color.parseColor("#ab1100")
    private val satisfiedColour = Color.parseColor("#00ab1c")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RequirementsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.ulr.observe(viewLifecycleOwner, {
            it?.let {
                binding.tvUlr.text = getString(R.string.tvUlrText, if (it) "Yes" else "No")
                setTextViewTextColour(binding.tvUlr, it)
            }
        })

        viewModel.csFoundations.observe(viewLifecycleOwner, {
            it?.let {
                binding.tvCsFoundations.text =
                    getString(R.string.tvCsFoundationsText, if (it) "Yes" else "No")
                setTextViewTextColour(binding.tvCsFoundations, it)
            }
        })

        viewModel.csBreadthAndDepth.observe(viewLifecycleOwner, {
            it?.let {
                binding.tvCsBreadthAndDepth.text =
                    getString(R.string.tvCsBreadthAndDepthText, if (it) "Yes" else "No")
                setTextViewTextColour(binding.tvCsBreadthAndDepth, it)
            }
        })

        viewModel.industrialExperience.observe(viewLifecycleOwner, {
            it?.let {
                binding.tvIndustrialExperience.text =
                    getString(R.string.tvIndustrialExperienceText, if (it) "Yes" else "No")
                setTextViewTextColour(binding.tvIndustrialExperience, it)
            }

        })

        viewModel.itProfessionalism.observe(viewLifecycleOwner, {
            it?.let {
                binding.tvItProfessionalism.text =
                    getString(R.string.tvItProfessionalismText, if (it) "Yes" else "No")
                setTextViewTextColour(binding.tvItProfessionalism, it)
            }

        })

        viewModel.mathematicsAndSciences.observe(viewLifecycleOwner, {
            it?.let {
                binding.tvMathematicsAndSciences.text =
                    getString(R.string.tvMathematicsAndSciencesText, if (it) "Yes" else "No")
                setTextViewTextColour(binding.tvMathematicsAndSciences, it)
            }
        })

        viewModel.credits.observe(viewLifecycleOwner, {
            it?.let {
                binding.tvCredits.text = getString(R.string.tvCreditsText, if (it) "Yes" else "No")
                setTextViewTextColour(binding.tvCredits, it)
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

    // Fragments outlive their views so need to clean up references to binding class instance
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}