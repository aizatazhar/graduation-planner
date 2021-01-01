package com.example.graduation_planner.models

data class Module(
    val moduleCode: String = "",

    val semestersOffered: List<Int> = listOf<Int>()
)