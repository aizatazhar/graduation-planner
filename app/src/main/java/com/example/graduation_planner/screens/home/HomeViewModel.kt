package com.example.graduation_planner.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.graduation_planner.database.SavedModulesDao
import com.example.graduation_planner.database.SavedModulesDatabase
import com.example.graduation_planner.models.GraduationRequirements
import com.example.graduation_planner.models.Module

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val dao: SavedModulesDao = SavedModulesDatabase.getInstance(application).savedModulesDao
    val modules: MutableList<Module> = mutableListOf()
    val liveModules: LiveData<List<Module>> = dao.getAllModules()

    val satisfiesUlr: Boolean = GraduationRequirements.satisfiesUniversityLevelRequirements(modules)
    val satisfiesCsFoundations: Boolean = GraduationRequirements.satisfiesComputerScienceFoundations(modules)
    val satisfiesCsBreadthAndDepth: Boolean = GraduationRequirements.satisfiesComputerScienceBreadthAndDepth(modules)
    val satisfiesIndustrialExperience: Boolean = GraduationRequirements.satisfiesIndustrialExperienceRequirements(modules)
    val satisfiesItProfessionalism: Boolean = GraduationRequirements.satisfiesItProfessionalism(modules)
    val satisfiesMathematicsAndSciences: Boolean = GraduationRequirements.satisfiesMathematicsAndSciences(modules)
    val satisfiesCredits: Boolean = GraduationRequirements.satisfiesCredits(modules)

    fun deleteModule(module: Module) {
        dao.delete(module)
    }

    fun clearAllModules() {
        dao.clear()
    }
}