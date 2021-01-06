package com.example.graduation_planner.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduation_planner.models.Module
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class SearchViewModel(private val moduleListJsonString: String) : ViewModel() {
    private val moduleList: List<Module>

    private val _displayList = MutableLiveData<MutableList<Module>>()
    val displayList: LiveData<MutableList<Module>>
        get() = _displayList

    init {
        moduleList = jsonStringToModuleList()
        _displayList.value = mutableListOf()
    }

    private fun fetchJsonFromApi(moduleQuery: String) {
        val academicYear = "2020-2021"
        val url = "https://api.nusmods.com/v2/$academicYear/modules/$moduleQuery.json"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get response")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                val module: Module = gson.fromJson(body, Module::class.java)
                val x = (module.semesterData)
            }
        })
    }

    fun filterModules(query: String) {
        val newDisplayList: MutableList<Module> = mutableListOf()
        val filteredList = moduleList.filter { module ->
            val uppercaseQuery = query.toUpperCase()
            module.moduleCode.toUpperCase().contains(uppercaseQuery)
                    || module.title.toUpperCase().contains(uppercaseQuery)
        }
        newDisplayList.addAll(filteredList)

        _displayList.value = newDisplayList
    }

    private fun jsonStringToModuleList() : List<Module> {
        val gson = GsonBuilder().create()
        return gson.fromJson(moduleListJsonString, Array<Module>::class.java).toList()
    }
}