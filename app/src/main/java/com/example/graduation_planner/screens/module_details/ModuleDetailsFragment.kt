package com.example.graduation_planner.screens.module_details

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.graduation_planner.R
import com.example.graduation_planner.databinding.ModuleDetailsFragmentBinding
import com.example.graduation_planner.models.FullModule
import com.example.graduation_planner.repository.Repository
import com.google.android.material.snackbar.Snackbar

class ModuleDetailsFragment : Fragment() {
    private var _binding: ModuleDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ModuleDetailsViewModel
    private lateinit var moduleCode: String
    private lateinit var selectedSemester: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ModuleDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository(requireActivity().application)
        val viewModelFactory = ModuleDetailsViewModelFactory(repository)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(ModuleDetailsViewModel::class.java)

        setFragmentResultListener("searchFragmentKey") { requestKey, bundle ->
            moduleCode = bundle.getString("moduleCode") ?: ""
            selectedSemester = bundle.getString("selectedSemester") ?: ""
            viewModel.setSelectedFullModule(moduleCode)
        }

        viewModel.isErrorLoading.observe(viewLifecycleOwner, {
            if (it) {
                binding.progressBar.visibility = View.GONE
                binding.details.layout.visibility = View.GONE
                binding.errorMessage.text = "Error loading module"
                binding.errorMessage.visibility = View.VISIBLE
            }
        })

        viewModel.selectedFullModule.observe(viewLifecycleOwner, { module ->
            module?.apply {
                binding.progressBar.visibility = View.GONE
                binding.details.layout.visibility = View.VISIBLE

                binding.details.moduleCode.text = moduleCode
                binding.details.moduleTitle.text = title
                binding.details.departmentFacultyCredits.text =
                    "${department} • ${faculty} • ${moduleCredit} MCs"
                binding.details.semesters.text = getSemestersText(this)
                binding.details.description.text = description

                if (prerequisite !== null) {
                    binding.details.prerequisite.text = prerequisite
                    binding.details.prerequisiteHeader.visibility = View.VISIBLE
                    binding.details.prerequisite.visibility = View.VISIBLE
                }

                if (corequisite !== null) {
                    binding.details.corequisite.text = corequisite
                    binding.details.corequisite.visibility = View.VISIBLE
                    binding.details.corequisiteHeader.visibility = View.VISIBLE
                }

                if (preclusion !== null) {
                    binding.details.preclusion.text = preclusion
                    binding.details.preclusion.visibility = View.VISIBLE
                    binding.details.preclusionHeader.visibility = View.VISIBLE
                }

                binding.saveButton.setOnClickListener {
                    viewModel.addModule(moduleCode, selectedSemester, ::showSuccessSnackBar, ::showErrorSnackBar)
                }
                binding.viewOnNusModsButton.setOnClickListener {
                    openLink(this)
                }
            }
        })
    }

    private fun getSemestersText(module: FullModule): String {
        var semestersText = ""
        module.semesterData.forEach { data ->
            run {
                semestersText += "Sem " + data.semester + " • "
            }
        }
        return semestersText.substring(0, semestersText.length - 3)
    }

    private fun openLink(module: FullModule) {
        val uris = Uri.parse("https://nusmods.com/modules/${module.moduleCode}/")
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        requireActivity().startActivity(intents)
    }

    private fun showSuccessSnackBar(message: String) {
        val snackBar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        snackBar.anchorView = requireActivity().findViewById(R.id.bottomNavigation)
        snackBar.view.apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.green_500))
            findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTextColor(
                Color.WHITE
            )
        }
        snackBar.show()
    }

    private fun showErrorSnackBar(message: String) {
        val snackBar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        snackBar.anchorView = requireActivity().findViewById(R.id.bottomNavigation)
        snackBar.view.apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.red_600))
            findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTextColor(
                Color.WHITE
            )
        }
        snackBar.show()
    }

    // Fragments outlive their views so need to clean up references to binding class instance
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}