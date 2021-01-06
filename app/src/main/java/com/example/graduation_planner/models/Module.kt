package com.example.graduation_planner.models

import com.google.gson.annotations.SerializedName

data class Module(
        @SerializedName("moduleCode")
        val moduleCode: String,

        @SerializedName("moduleCredit")
        val moduleCredit: Int,

        @SerializedName("semesterData")
        val semesterData: List<SemesterData> = listOf()
)

data class SemesterData(
        @SerializedName("semester")
        val semester: Int
)