package com.example.graduation_planner.screens.requirements

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.graduation_planner.R
import com.example.graduation_planner.screens.module_list.ModuleListViewModel
import com.example.graduation_planner.screens.module_list.ModuleListViewModelFactory

class RequirementsFragment : Fragment() {
    private lateinit var viewModel: ModuleListViewModel
    private var notSatisfiedColour = Color.parseColor("#ab1100")
    private var satisfiedColour = Color.parseColor("#00ab1c")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_requirements, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ModuleListViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ModuleListViewModel::class.java)

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

        return root;
    }

    private fun setTextViewTextColour(textView: TextView, isSatisfied: Boolean) {
        if (isSatisfied) {
            textView.setTextColor(satisfiedColour)
        } else {
            textView.setTextColor(notSatisfiedColour)
        }
    }
}