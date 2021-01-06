package com.example.graduation_planner.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduation_planner.models.Module
import com.example.graduation_planner.models.SampleModules
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class SearchViewModel : ViewModel() {
    private val moduleList = fetchSampleData()

    private val _displayList = MutableLiveData<MutableList<Module>>()
    val displayList: LiveData<MutableList<Module>>
        get() = _displayList

    init {
        _displayList.value = fetchSampleData()
    }

    private fun fetchSampleData(): MutableList<Module> {
        val result: MutableList<Module> = mutableListOf()
        result.addAll(SampleModules.getSampleModules())
        return result
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

    fun findModules(query: String) {
        val filteredList = moduleList.filter { module ->
            module.moduleCode.toUpperCase().contains(query.toUpperCase())
        }

        _displayList.value = filteredList as MutableList<Module>?
        println(_displayList.value)
    }
}