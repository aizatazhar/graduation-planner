package com.example.graduation_planner.screens.module_details

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
import java.net.UnknownHostException

class ModuleDetailsViewModel(private val repository: Repository) : ViewModel() {
    private val _selectedFullModule = MutableLiveData<FullModule>()
    val selectedFullModule: LiveData<FullModule>
        get() = _selectedFullModule

    private val _isErrorLoading = MutableLiveData<Boolean>(false)
    val isErrorLoading: LiveData<Boolean>
        get() = _isErrorLoading

    fun setSelectedFullModule(moduleCode: String) {
        _selectedFullModule.value = null
        _isErrorLoading.value = false

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fullModule = repository.fetchFullModuleData(moduleCode)
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

    fun addModule(
        moduleCode: String,
        selectedSemester: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    repository.fetchModuleDataAndInsertIntoRoomDatabase(
                        moduleCode,
                        selectedSemester
                    )
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

    fun deleteModule(moduleCode: String) {
        repository.deleteModule(moduleCode)
    }
}