package com.example.graduation_planner.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.graduation_planner.databinding.SemesterFragmentBinding

class SemesterFragment : Fragment() {
    private var _binding: SemesterFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SemesterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCheckedRadioButton(view)
        binding.applyButton.setOnClickListener {
            viewModel.selectedSemester = getSelectedSemesterId(view)
            it.findNavController().popBackStack()
        }
    }

    private fun setCheckedRadioButton(view: View) {
        // Get the integer id of the radio button from its string version
        val id: Int =
            resources.getIdentifier(viewModel.selectedSemester, "id", requireContext().packageName)
        val radioButton: RadioButton = view.findViewById(id)
        radioButton.isChecked = true
    }

    private fun getSelectedSemesterId(view: View): String {
        val radioButtonId: Int = binding.radioGroup.checkedRadioButtonId
        // Return the string version of the radio button's id
        return resources.getResourceEntryName(radioButtonId)
    }

    // Fragments outlive their views so need to clean up references to binding class instance
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}