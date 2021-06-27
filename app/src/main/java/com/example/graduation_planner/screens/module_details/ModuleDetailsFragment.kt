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
import androidx.navigation.fragment.findNavController
import com.example.graduation_planner.R
import com.example.graduation_planner.databinding.ModuleDetailsFragmentBinding
import com.example.graduation_planner.models.FullModule
import com.example.graduation_planner.repository.Repository
import com.google.android.material.snackbar.Snackbar

class ModuleDetailsFragment : Fragment() {
    private var _binding: ModuleDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ModuleDetailsViewModel
    private var moduleCode: String = ""
    private var selectedSemester: String = ""

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
        viewModel = ViewModelProvider(this, viewModelFactory).get(ModuleDetailsViewModel::class.java)

        // Callback is active until the fragment is destroyed, so if the fragment is recreated,
        // the callback will be inactive
        setFragmentResultListener("searchFragmentKey") { requestKey, bundle ->
            binding.saveButton.text = "Save module"
            bundle.apply {
                moduleCode = getString("moduleCode") ?: ""
                selectedSemester = getString("selectedSemester") ?: ""
            }
            viewModel.setSelectedFullModule(moduleCode)
        }

        setFragmentResultListener("moduleFragmentKey") { requestKey, bundle ->
            binding.saveButton.text = "Delete module"
            moduleCode = bundle.getString("moduleCode") ?: ""
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
                    if (selectedSemester == "") {
                        viewModel.deleteModule(moduleCode, ::showSnackBar)
                        findNavController().popBackStack()
                    } else {
                        viewModel.addModule(moduleCode, selectedSemester, ::showSnackBar)
                    }
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

    private fun showSnackBar(message: String, isSuccessful: Boolean) {
        val snackBar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        snackBar.anchorView = requireActivity().findViewById(R.id.bottomNavigation)
        snackBar.view.apply {
            if (isSuccessful) {
                setBackgroundColor(ContextCompat.getColor(context, R.color.green_500))
            } else {
                setBackgroundColor(ContextCompat.getColor(context, R.color.red_600))
            }
            findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTextColor(
                Color.WHITE
            )
        }
        snackBar.show()
    }

    // The variables are lost when the fragment is destroyed (e.g. navigating to another fragment
    // in bottom navigation), so we use save the variables and restore them when the fragment
    // is recreated
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("moduleCode", moduleCode)
        outState.putString("selectedSemester", selectedSemester)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        moduleCode = savedInstanceState?.getString("moduleCode") ?: ""
        selectedSemester = savedInstanceState?.getString("selectedSemester") ?: ""
        if (selectedSemester == "") {
            binding.saveButton.text = "Delete module"
        } else {
            binding.saveButton.text = "Save module"
        }
    }

    // Fragments outlive their views so need to clean up references to binding class instance
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}