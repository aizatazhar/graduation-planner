package com.example.graduation_planner.screens.module_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.graduation_planner.repository.Repository

class ModuleDetailsViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ModuleDetailsViewModel(repository) as T
    }
}