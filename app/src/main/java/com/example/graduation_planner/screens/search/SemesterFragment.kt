package com.example.graduation_planner.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.graduation_planner.R


class SemesterFragment : Fragment() {
    private val viewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.semester_fragment, container, false)

        setCheckedRadioButton(root)

        val applyButton: Button = root.findViewById(R.id.applyButton)
        applyButton.setOnClickListener {
            viewModel.selectedSemester = getSelectedSemesterId(root)
            it.findNavController().popBackStack()
        }

        return root
    }

    private fun setCheckedRadioButton(view: View) {
        // Get the integer id of the radio button from its string version
        val id: Int =
            resources.getIdentifier(viewModel.selectedSemester, "id", requireContext().packageName)
        val radioButton: RadioButton = view.findViewById(id)
        radioButton.isChecked = true
    }

    private fun getSelectedSemesterId(view: View): String {
        val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)
        val radioButtonId: Int = radioGroup.checkedRadioButtonId
        // Return the string version of the radio button's id
        return resources.getResourceEntryName(radioButtonId)
    }
}