package com.example.graduation_planner.screens.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.graduation_planner.database.SavedModulesDao
import com.example.graduation_planner.database.SavedModulesDatabase
import com.example.graduation_planner.models.Module
import com.example.graduation_planner.models.SemesterData
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.IOException
import java.util.*

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val moduleList: List<Module>
    private val _displayList = MutableLiveData<MutableList<Module>>()
    val displayList: LiveData<MutableList<Module>>
        get() = _displayList

    private val dao: SavedModulesDao

    init {
        moduleList = readModuleListJson()
        _displayList.value = mutableListOf()
        dao = SavedModulesDatabase.getInstance(application).savedModulesDao
    }

    fun filterModules(query: String) {
        if (query.isEmpty()) {
            _displayList.value = mutableListOf()
            return
        }

        val newDisplayList: MutableList<Module> = mutableListOf()
        val filteredList = moduleList.filter { module ->
            val uppercaseQuery = query.toUpperCase(Locale.getDefault())
            module.moduleCode.toUpperCase(Locale.getDefault()).contains(uppercaseQuery)
                    || module.title.toUpperCase(Locale.getDefault()).contains(uppercaseQuery)
        }
        newDisplayList.addAll(filteredList)
        _displayList.value = newDisplayList
    }

    private fun readModuleListJson() : List<Module> {
        val jsonString = getApplication<Application>().assets.open("moduleList.json").bufferedReader().use {
            it.readText()
        }
        val gson = GsonBuilder().create()
        return gson.fromJson(jsonString, Array<Module>::class.java).toList()
    }

    fun fetchModuleFromApiAndInsertIntoDatabase(module: Module) {
        val moduleQuery = module.moduleCode
        val academicYear = "2020-2021"
        val url = "https://api.nusmods.com/v2/$academicYear/modules/$moduleQuery.json"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get response")
            }

            override fun onResponse(call: Call, response: Response) {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        val body = response.body?.string()
                        val gson = GsonBuilder().create()

                        val moduleToAdd: Module = gson.fromJson(body, Module::class.java)
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

    fun clearSavedModules() {
        dao.clear()
    }
}