package com.example.graduation_planner.screens.modules

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.graduation_planner.database.SavedModulesDao
import com.example.graduation_planner.database.SavedModulesDatabase
import com.example.graduation_planner.models.GraduationRequirements
import com.example.graduation_planner.models.Module
import kotlinx.coroutines.launch

class ModulesViewModel(application: Application) : AndroidViewModel(application) {
    private val dao: SavedModulesDao = SavedModulesDatabase.getInstance(application).savedModulesDao

    val modules: MutableList<Module> = mutableListOf()
    val liveModules: LiveData<List<Module>> = dao.getAll()

    private val _ulr = MutableLiveData(false)
    val ulr: LiveData<Boolean>
        get() = _ulr

    private val _csFoundations = MutableLiveData(false)
    val csFoundations: LiveData<Boolean>
        get() = _csFoundations

    private val _csBreadthAndDepth = MutableLiveData(false)
    val csBreadthAndDepth: LiveData<Boolean>
        get() = _csBreadthAndDepth

    private val _industrialExperience = MutableLiveData(false)
    val industrialExperience: LiveData<Boolean>
        get() = _industrialExperience

    private val _itProfessionalism = MutableLiveData(false)
    val itProfessionalism: LiveData<Boolean>
        get() = _itProfessionalism

    private val _mathematicsAndSciences = MutableLiveData(false)
    val mathematicsAndSciences: LiveData<Boolean>
        get() = _mathematicsAndSciences

    private val _credits = MutableLiveData(false)
    val credits: LiveData<Boolean>
        get() = _credits

    fun deleteModule(module: Module) {
        dao.delete(module)
    }

    fun clearAllModules() {
        dao.clear()
    }

    fun recalculateGraduationRequirements() {
        viewModelScope.launch {
            liveModules.value?.let {
                _ulr.value = GraduationRequirements.satisfiesUniversityLevelRequirements(it)
                _csFoundations.value = GraduationRequirements.satisfiesComputerScienceFoundations(it)
                _csBreadthAndDepth.value = GraduationRequirements.satisfiesComputerScienceBreadthAndDepth(it)
                _industrialExperience.value = GraduationRequirements.satisfiesIndustrialExperienceRequirements(it)
                _itProfessionalism.value = GraduationRequirements.satisfiesItProfessionalism(it)
                _mathematicsAndSciences.value = GraduationRequirements.satisfiesMathematicsAndSciences(it)
                _credits.value = GraduationRequirements.satisfiesCredits(it)
            }
        }
    }
}