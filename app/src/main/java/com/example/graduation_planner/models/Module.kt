package com.example.graduation_planner.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "saved_modules")
data class Module(
        @PrimaryKey(autoGenerate = false) @SerializedName("moduleCode")
        var moduleCode: String = "",

        @SerializedName("title")
        var title: String = "",

        @SerializedName("moduleCredit")
        var moduleCredit: Int = -1,

        @Ignore @SerializedName("semesters")
        var semesters: List<Int> = listOf(),

        var inSemOne: Boolean = false,

        var inSemTwo: Boolean = false,

        @Ignore @SerializedName("semesterData")
        var semesterData: List<SemesterData> = listOf()
) {
        constructor(): this("", "", -1, listOf(), false, false, listOf())
}

data class SemesterData(
        @SerializedName("semester")
        val semester: Int = -1
)