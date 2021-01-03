package com.example.graduation_planner.models

data class Module(
    val moduleCode: String = "",

    val credits: Int,

    val semestersOffered: List<Int> = listOf<Int>()
)