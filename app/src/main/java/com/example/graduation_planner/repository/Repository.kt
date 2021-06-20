package com.example.graduation_planner.repository

import android.app.Application
import com.example.graduation_planner.database.SavedModulesDao
import com.example.graduation_planner.database.SavedModulesDatabase
import com.example.graduation_planner.models.Module
import com.example.graduation_planner.models.SemesterData
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import okio.IOException

class Repository(val application: Application) {
    private val dao: SavedModulesDao = SavedModulesDatabase.getInstance(application).savedModulesDao

    fun getModuleListFromJson(): List<Module> {
        val jsonString = application.assets.open("moduleList.json").bufferedReader().use {
            it.readText()
        }
        val gson = GsonBuilder().create()
        return gson.fromJson(jsonString, Array<Module>::class.java).toList()
    }

    fun fetchModuleDataAndInsertIntoRoomDatabase(module: Module, selectedSemester: String) {
        val moduleQuery = module.moduleCode
        val academicYear = "2020-2021"
        val url = "https://api.nusmods.com/v2/$academicYear/modules/$moduleQuery.json"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get response")
            }

            override fun onResponse(call: Call, response: Response) {
                GlobalScope.launch {
                    withContext(Dispatchers.IO) {
                        val body = response.body?.string()
                        val gson = GsonBuilder().create()

                        val moduleToAdd: Module = gson.fromJson(body, Module::class.java)
                        moduleToAdd.selectedSemester = selectedSemester
                        for (data: SemesterData in moduleToAdd.semesterData) {
                            if (data.semester == 1) {
                                moduleToAdd.inSemOne = true
                            }

                            if (data.semester == 2) {
                                moduleToAdd.inSemTwo = true
                            }
                        }

                        dao.insert(moduleToAdd)
                    }
                }
            }
        })
    }
}