package com.example.graduation_planner.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduation_planner.models.FullModule
import com.example.graduation_planner.models.Module
import com.example.graduation_planner.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.UnknownHostException
import java.util.*

class SearchViewModel(private val repository: Repository) : ViewModel() {
    private val moduleList: List<Module> = repository.getModuleListFromJson()

    private val _displayList = MutableLiveData<MutableList<Module>>(mutableListOf())
    val displayList: LiveData<MutableList<Module>>
        get() = _displayList

    var selectedSemester: String = "y1s1"

    private val _selectedFullModule = MutableLiveData<FullModule>()
    val selectedFullModule: LiveData<FullModule>
        get() = _selectedFullModule
    private val _isErrorLoading = MutableLiveData<Boolean>(false)
    val isErrorLoading: LiveData<Boolean>
        get() = _isErrorLoading

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

    fun addModule(moduleCode: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    repository.fetchModuleDataAndInsertIntoRoomDatabase(moduleCode, selectedSemester)
                    onSuccess("Added module $moduleCode")
                } catch (e: UnknownHostException) {
                    e.printStackTrace()
                    onFailure("An error occurred. Please check your internet connection")
                } catch (e: Exception) {
                    e.printStackTrace()
                    onFailure(e.message.toString())
                }
            }
        }
    }

    fun setSelectedFullModule(module: Module)  {
        _selectedFullModule.value = null
        _isErrorLoading.value = false

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fullModule = repository.fetchFullModuleData(module.moduleCode)
                _selectedFullModule.postValue(fullModule)
            } catch (e: UnknownHostException) {
                _isErrorLoading.postValue(true)
                e.printStackTrace()
            } catch (e: Exception) {
                _isErrorLoading.postValue(true)
                e.printStackTrace()
            }
        }
    }
}