package com.example.graduation_planner.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.graduation_planner.databinding.ModuleDetailsFragmentBinding
import com.example.graduation_planner.databinding.SearchFragmentBinding
import com.example.graduation_planner.repository.Repository

class ModuleDetailsFragment : Fragment() {
    private var _binding: ModuleDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SearchViewModel

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
        val viewModelFactory = SearchViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(SearchViewModel::class.java)

        viewModel.isErrorLoading.observe(viewLifecycleOwner, {
            if (it) {
                binding.progressBar.visibility = View.GONE
                binding.details.moduleTitle.text = "Error loading module"
            }
        })

        viewModel.selectedFullModule.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.progressBar.visibility = View.GONE
                binding.details.moduleCode.text = it.moduleCode
                binding.details.moduleTitle.text = it.title
                binding.details.departmentFacultyCredits.text = "${it.department} • ${it.faculty} • ${it.moduleCredit} MCs"

                var semestersText = ""
                it.semesterData.forEach{data ->
                    run {
                        semestersText += "Sem " + data.semester + " • "
                    }
                }
                semestersText = semestersText.substring(0, semestersText.length - 3)
                binding.details.semesters.text = semestersText

                binding.details.description.text = it.description

                if (it.prerequisite !== null) {
                    binding.details.prerequisite.text = it.prerequisite
                    binding.details.prerequisite.visibility = View.VISIBLE
                    binding.details.prerequisiteHeader.visibility = View.VISIBLE
                }

                if (it.corequisite !== null) {
                    binding.details.corequisite.text = it.corequisite
                    binding.details.corequisite.visibility = View.VISIBLE
                    binding.details.corequisiteHeader.visibility = View.VISIBLE
                }

                if (it.preclusion !== null) {
                    binding.details.preclusion.text = it.preclusion
                    binding.details.preclusion.visibility = View.VISIBLE
                    binding.details.preclusionHeader.visibility = View.VISIBLE
                }
            }
        })
    }

    // Fragments outlive their views so need to clean up references to binding class instance
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}