package com.example.graduation_planner.repository

import android.app.Application
import com.example.graduation_planner.models.Module
import com.google.gson.GsonBuilder

class Repository(val application: Application) {

    fun getModuleListFromJson(): List<Module> {
        val jsonString = application.assets.open("moduleList.json").bufferedReader().use {
            it.readText()
        }
        val gson = GsonBuilder().create()
        return gson.fromJson(jsonString, Array<Module>::class.java).toList()
    }
}