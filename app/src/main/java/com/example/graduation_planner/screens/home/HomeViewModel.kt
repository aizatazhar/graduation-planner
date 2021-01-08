package com.example.graduation_planner.screens.home

import androidx.lifecycle.ViewModel
import com.example.graduation_planner.models.GraduationRequirements
import com.example.graduation_planner.models.Module
import com.example.graduation_planner.models.SampleModules

class HomeViewModel : ViewModel() {
    val modules: List<Module> = SampleModules.getSampleModules()
    val satisfiesUlr: Boolean = GraduationRequirements.satisfiesUniversityLevelRequirements(modules)
    val satisfiesCsFoundations: Boolean = GraduationRequirements.satisfiesComputerScienceFoundations(modules)
    val satisfiesCsBreadthAndDepth: Boolean = GraduationRequirements.satisfiesComputerScienceBreadthAndDepth(modules)
    val satisfiesIndustrialExperience: Boolean = GraduationRequirements.satisfiesIndustrialExperienceRequirements(modules)
    val satisfiesItProfessionalism: Boolean = GraduationRequirements.satisfiesItProfessionalism(modules)
    val satisfiesMathematicsAndSciences: Boolean = GraduationRequirements.satisfiesMathematicsAndSciences(modules)
    val satisfiesCredits: Boolean = GraduationRequirements.satisfiesCredits(modules)
}