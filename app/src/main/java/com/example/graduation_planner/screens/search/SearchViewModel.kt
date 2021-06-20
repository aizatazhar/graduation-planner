package com.example.graduation_planner.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduation_planner.models.Module
import com.example.graduation_planner.repository.Repository
import java.util.*

class SearchViewModel(private val repository: Repository) : ViewModel() {
    private val moduleList: List<Module> = repository.getModuleListFromJson()

    private val _displayList = MutableLiveData<MutableList<Module>>()
    val displayList: LiveData<MutableList<Module>>
        get() = _displayList

    var selectedSemester: String = "y1s1"

    init {
        _displayList.value = mutableListOf()
    }

    fun filterModules(query: String) {
        if (query.isEmpty()) {
            _displayList.value = mutableListOf()
            return
        }

        val newDisplayList: MutableList<Module> = mutableListOf()
        val filteredList = moduleList.filter { module ->
            val uppercaseQuery = query.toUpperCase(Locale.getDefault())
            module.moduleCode.toUpperCase(Locale.getDefault()).contains(uppercaseQuery)
                    || module.title.toUpperCase(Locale.getDefault()).contains(uppercaseQuery)
        }
        newDisplayList.addAll(filteredList)
        _displayList.value = newDisplayList
    }

    fun addModule(module: Module) {
        repository.fetchModuleDataAndInsertIntoRoomDatabase(module, selectedSemester)
    }
}