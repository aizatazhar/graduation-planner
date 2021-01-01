package com.example.graduation_planner.screens.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.graduation_planner.R
import com.example.graduation_planner.models.SampleModules

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.home_fragment, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        val textView: TextView = root.findViewById(R.id.textView)
        textView.text = SampleModules.getSampleModules().toString()

        return root
    }
}