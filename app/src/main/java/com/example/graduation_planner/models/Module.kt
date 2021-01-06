package com.example.graduation_planner.models

import com.google.gson.annotations.SerializedName

data class Module(
        @SerializedName("moduleCode")
        val moduleCode: String,

        @SerializedName("title")
        val title: String = "",

        @SerializedName("moduleCredit")
        val moduleCredit: Int,

        @SerializedName("semesters")
        val semesters: List<Int> = listOf(),

        @SerializedName("semesterData")
        val semesterData: List<SemesterData> = listOf()
)

data class SemesterData(
        @SerializedName("semester")
        val semester: Int
)