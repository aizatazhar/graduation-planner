package com.example.graduation_planner.models

data class FullModule(
    val acadYear: String,
    val aliases: List<String>,
    val attributes: Attributes,
    val corequisite: String,
    val department: String,
    val description: String,
    val faculty: String,
    val fulfillRequirements: List<String>,
    val moduleCode: String,
    val moduleCredit: String,
    val preclusion: String,
    val prereqTree: Any,
    val prerequisite: String,
    val semesterData: List<SemesterData>,
    val title: String,
    val workload: List<Number>
)

data class Attributes(
    val mpes1: Boolean,
    val mpes2: Boolean
)

data class PrereqTree(
    val or: List<Any>
)

data class SemesterData(
    val covidZones: List<String>,
    val examDate: String,
    val examDuration: Int,
    val semester: Int,
    val timetable: List<Timetable>
)

data class Timetable(
    val classNo: String,
    val covidZone: String,
    val day: String,
    val endTime: String,
    val lessonType: String,
    val size: Int,
    val startTime: String,
    val venue: String,
    val weeks: List<Int>
)